package at.htl;

import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DynamicKOTests {

    @Test
    public void fourTeamsDiagramTest() {
        Filewriter filewriter = new Filewriter();

        Tournament tournament = new Tournament("Four-Teams-Test");

        Team team01 = new Team("team01");
        Team team02 = new Team("team02");
        Team team03 = new Team("team03");
        Team team04 = new Team("team04");

        Match match12 = new Match(team01, team02);
        Match match34 = new Match(team03, team04);

        Node node01 = new Node(match12);
        Node node02 = new Node(match34);
        Node node03 = new Node();

        Phase phase1 = new Phase(2);
        Phase phase2 = new Phase(1);

        node01.setPhase(phase1);
        node02.setPhase(phase1);

        match12.setPointsTeam1(2);
        match12.setPointsTeam2(1);

        match34.setPointsTeam1(0);
        match34.setPointsTeam2(3);

        Match match03 = new Match(match12.getWinningTeam(), match34.getWinningTeam());

        node03.setCurMatch(match03);
        node03.setPhase(phase2);

        List phases = new ArrayList();
        phases.add(phase1);
        phases.add(phase2);

        tournament.setPhases(phases);

        match03.setPointsTeam1(1);
        match03.setPointsTeam2(0);

        node01.setParentNode(node03);
        node02.setParentNode(node03);

        filewriter.writeFinalResult(node03, tournament);



    }

}
