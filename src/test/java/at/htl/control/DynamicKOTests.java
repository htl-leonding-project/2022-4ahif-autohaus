package at.htl.control;

import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DynamicKOTests {

    //TODO: Teams automatisch zu zufälligen Matches zuweisen
    //TODO: Node Verbindungen automatisch erstellen
    //TODO: Phasen Level und Nodes automatisch zuweisen

    /**
    Es werden vier Teams erstellt.
    Ein Turnier nach K.O.-Phasen System wird erstellt und durchgeführt.
    Die Spielergebnisse werden vom Test vorgegeben und sollten den Turnierleiter simulieren.

    Das "Four-Teams-Test.png" ist das Ergebnis.
     */
    @Test
    public void KO_four_teams() {
        Filewriter filewriter = new Filewriter();

        Tournament tournament = new Tournament("Four-Teams-Test");

        Team team01 = new Team("team01", "t1");
        Team team02 = new Team("team02", "t2");
        Team team03 = new Team("team03", "t3");
        Team team04 = new Team("team04", "t4");

        Match match12 = new Match(team01, team02);
        Match match34 = new Match(team03, team04);

        Node node01 = new Node(match12);
        Node node02 = new Node(match34);
        Node node03 = new Node();

        Phase phase1 = new Phase(2);
        Phase phase2 = new Phase(1);

        node01.setPhase(phase1);
        node02.setPhase(phase1);

        //Spielergebnisse werden gesetzt
        match12.setPointsTeam1(2);
        match12.setPointsTeam2(1);

        match34.setPointsTeam1(0);
        match34.setPointsTeam2(3);

        //Die Beiden Gewinner spielen gegeneinander
        Match match03 = new Match(match12.getWinningTeam(), match34.getWinningTeam());

        node03.setCurMatch(match03);
        node03.setPhase(phase2);

        List<Phase> phases = new ArrayList<>();
        phases.add(phase1);
        phases.add(phase2);

        tournament.setPhases(phases);

        match03.setPointsTeam1(1);
        match03.setPointsTeam2(0);

        node01.setParentNode(node03);
        node02.setParentNode(node03);

        filewriter.writeFinalResult(node03, tournament);
    }

    /**
    Es werden acht Teams erstellt.
    Ein Turnier nach K.O.-System wird erstellt und ausgespielt.
    Die Spielergebnisse werden vom Test vorgegeben und sollten den Turnierleiter simulieren.

    Das "Eight-Teams-Test.png" ist das Ergebnis.
     */
    @Test
    public void KO_eight_teams() {
        Filewriter filewriter = new Filewriter();

        Tournament tournament = new Tournament("Eight-Teams-Test");

        Team team01 = new Team("team01", "t1");
        Team team02 = new Team("team02", "t2");
        Team team03 = new Team("team03", "t3");
        Team team04 = new Team("team04", "t4");
        Team team05 = new Team("team05", "t5");
        Team team06 = new Team("team06", "t6");
        Team team07 = new Team("team07", "t7");
        Team team08 = new Team("team08", "t8");

        //Namensgebung der Matches
        //match12 -> team01 spielt gegen team02
        Match match12 = new Match(team01, team02);
        Match match34 = new Match(team03, team04);
        Match match56 = new Match(team05, team06);
        Match match78 = new Match(team07, team08);

        Node node01 = new Node(match12);
        Node node02 = new Node(match34);
        Node node03 = new Node();
        Node node04 = new Node(match56);
        Node node05 = new Node(match78);
        Node node06 = new Node();
        Node node07 = new Node();

        Phase phase1 = new Phase(3);
        Phase phase2 = new Phase(2);
        Phase phase3 = new Phase(1);

        node01.setPhase(phase1);
        node02.setPhase(phase1);
        node04.setPhase(phase1);
        node05.setPhase(phase1);

        //Die Spielergebnisse werden gesetzt
        match12.setPointsTeam1(2);
        match12.setPointsTeam2(1);

        match34.setPointsTeam1(0);
        match34.setPointsTeam2(3);

        match56.setPointsTeam1(0);
        match56.setPointsTeam2(1);

        match78.setPointsTeam1(1);
        match78.setPointsTeam2(3);

        //Halbfinale
        Match match03 = new Match(match12.getWinningTeam(), match34.getWinningTeam());
        Match match06 = new Match(match56.getWinningTeam(), match78.getWinningTeam());

        node03.setCurMatch(match03);
        node03.setPhase(phase2);
        node06.setCurMatch(match06);
        node06.setPhase(phase2);

        List<Phase> phases = new ArrayList<>();
        phases.add(phase1);
        phases.add(phase2);
        phases.add(phase3);

        tournament.setPhases(phases);

        match03.setPointsTeam1(1);
        match03.setPointsTeam2(0);

        match06.setPointsTeam1(1);
        match06.setPointsTeam2(2);

        node01.setParentNode(node03);
        node02.setParentNode(node03);

        node04.setParentNode(node06);
        node05.setParentNode(node06);

        node03.setParentNode(node07);
        node06.setParentNode(node07);

        //Finalspiel
        Match match07 = new Match(match03.getWinningTeam(), match06.getWinningTeam());

        match07.setPointsTeam1(1);
        match07.setPointsTeam2(0);

        node07.setCurMatch(match07);

        node07.setPhase(phase3);
        filewriter.writeFinalResult(node07, tournament);
    }

    /**
    Wie bei den Tests darüber, wird ein Turnier nach K.O.-System simuliert.
    Es werden zu Beginn 16 Teams erstellt.
    Der Test gibt die Spielergebnisse vor, was den Turnierleiter simulieren sollte.

    Das Ergebiss ist die "Sixteen-Teams-Test.png" Grafik.
     */

    @Test
    public void KO_sixteen_teams() {
        Filewriter filewriter = new Filewriter();

        Tournament tournament = new Tournament("Sixteen-Teams-Test");

        Team team01 = new Team("team01", "t1");
        Team team02 = new Team("team02", "t2");
        Team team03 = new Team("team03", "t3");
        Team team04 = new Team("team04", "t4");
        Team team05 = new Team("team05", "t5");
        Team team06 = new Team("team06", "t6");
        Team team07 = new Team("team07", "t7");
        Team team08 = new Team("team08", "t8");
        Team team09 = new Team("team09", "t9");
        Team team10 = new Team("team10", "t10");
        Team team11 = new Team("team11", "t11");
        Team team12 = new Team("team12", "t12");
        Team team13 = new Team("team13", "t13");
        Team team14 = new Team("team14", "t14");
        Team team15 = new Team("team15", "t15");
        Team team16 = new Team("team16", "t16");

        Match match1 = new Match(team01, team02);
        Match match2 = new Match(team03, team04);
        Match match3 = new Match(team05, team06);
        Match match4 = new Match(team07, team08);
        Match match5 = new Match(team09, team10);
        Match match6 = new Match(team11, team12);
        Match match7 = new Match(team13, team14);
        Match match8 = new Match(team15, team16);

        Node node01 = new Node(match1);
        Node node02 = new Node(match2);
        Node node03 = new Node(match3);
        Node node04 = new Node(match4);
        Node node05 = new Node(match5);
        Node node06 = new Node(match6);
        Node node07 = new Node(match7);
        Node node08 = new Node(match8);

        Node node09 = new Node();
        Node node10 = new Node();
        Node node11 = new Node();
        Node node12 = new Node();

        Node node13 = new Node();
        Node node14 = new Node();

        Node node15 = new Node();

        Phase phase1 = new Phase(4);
        Phase phase2 = new Phase(3);
        Phase phase3 = new Phase(2);
        Phase phase4 = new Phase(1);

        node01.setPhase(phase1);
        node02.setPhase(phase1);
        node03.setPhase(phase1);
        node04.setPhase(phase1);
        node05.setPhase(phase1);
        node06.setPhase(phase1);
        node07.setPhase(phase1);
        node08.setPhase(phase1);

        match1.setPointsTeam1(2);
        match1.setPointsTeam2(1);

        match2.setPointsTeam1(0);
        match2.setPointsTeam2(3);

        match3.setPointsTeam1(0);
        match3.setPointsTeam2(1);

        match4.setPointsTeam1(1);
        match4.setPointsTeam2(3);

        match5.setPointsTeam1(0);
        match5.setPointsTeam2(4);

        match6.setPointsTeam1(0);
        match6.setPointsTeam2(2);

        match7.setPointsTeam1(3);
        match7.setPointsTeam2(2);

        match8.setPointsTeam1(0);
        match8.setPointsTeam2(1);

        Match match9 = new Match(match1.getWinningTeam(), match2.getWinningTeam());
        Match match10 = new Match(match3.getWinningTeam(), match4.getWinningTeam());
        Match match11 = new Match(match5.getWinningTeam(), match6.getWinningTeam());
        Match match12 = new Match(match7.getWinningTeam(), match8.getWinningTeam());

        node09.setCurMatch(match9);
        node09.setPhase(phase2);
        node10.setCurMatch(match10);
        node10.setPhase(phase2);
        node11.setCurMatch(match11);
        node11.setPhase(phase2);
        node12.setCurMatch(match12);
        node12.setPhase(phase2);

        List<Phase> phases = new ArrayList<>();
        phases.add(phase1);
        phases.add(phase2);
        phases.add(phase3);
        phases.add(phase4);

        tournament.setPhases(phases);

        match9.setPointsTeam1(3);
        match9.setPointsTeam2(1);

        match10.setPointsTeam1(3);
        match10.setPointsTeam2(2);

        match11.setPointsTeam1(1);
        match11.setPointsTeam2(2);

        match12.setPointsTeam1(0);
        match12.setPointsTeam2(1);

        node01.setParentNode(node09);
        node02.setParentNode(node09);

        node03.setParentNode(node10);
        node04.setParentNode(node10);

        node05.setParentNode(node11);
        node06.setParentNode(node11);

        node07.setParentNode(node12);
        node08.setParentNode(node12);

        node09.setParentNode(node13);
        node10.setParentNode(node13);

        node11.setParentNode(node14);
        node12.setParentNode(node14);

        node13.setParentNode(node15);
        node14.setParentNode(node15);

        Match match13 = new Match(match9.getWinningTeam(), match10.getWinningTeam());
        Match match14 = new Match(match11.getWinningTeam(), match12.getWinningTeam());

        match13.setPointsTeam1(1);
        match13.setPointsTeam2(0);

        match14.setPointsTeam1(2);
        match14.setPointsTeam2(1);

        node13.setCurMatch(match13);
        node13.setPhase(phase3);

        node14.setCurMatch(match14);
        node14.setPhase(phase3);

        Match match15 = new Match(match13.getWinningTeam(), match14.getWinningTeam());


        match15.setPointsTeam1(1);
        match15.setPointsTeam2(0);

        node15.setCurMatch(match15);
        node15.setPhase(phase4);

        filewriter.writeFinalResult(node15, tournament);
    }
}
