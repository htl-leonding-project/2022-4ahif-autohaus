package at.htl;

import at.htl.filewriter.*;
import org.junit.jupiter.api.Test;
import at.htl.entity.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class EntityTest {

    @Test
    public void getWinningTeamTest(){
        final String name1 = "team01";
        final String name2 = "team02";

        Team team01 = new Team(name1);
        Team team02 = new Team(name2);
        Match match = new Match();
        match.setTeam1(team01);
        match.setTeam2(team02);
        match.setPointsTeam1(0);
        match.setPointsTeam2(1);

        assertThat(match.getWinningTeam().getName()).isEqualTo(name2);
    }

    @Test
    public void getWinningTeamOfMoreThanOneMatch(){
        final String name1 = "team01";
        final String name2 = "team02";
        final String name3 = "team03";
        final String name4 = "team04";

        Team team01 = new Team(name1);
        Team team02 = new Team(name2);
        Team team03 = new Team(name3);
        Team team04 = new Team(name4);

        Match match01 = new Match();
        Match match02 = new Match();
        Match match03 = new Match();

        match01.setTeam1(team01);
        match01.setTeam2(team02);
        match01.setPointsTeam1(3);
        match01.setPointsTeam2(1);

        match02.setTeam1(team03);
        match02.setTeam2(team04);
        match02.setPointsTeam1(1);
        match02.setPointsTeam2(3);

        match03.setTeam1(match01.getWinningTeam());
        match03.setTeam2(match02.getWinningTeam());
        match03.setPointsTeam1(3);
        match03.setPointsTeam2(4);

        assertThat(match03.getWinningTeam().getName()).isEqualTo("team04");
    }

    @Test
    public void getWinningTeamWithNodes(){
        final String name1 = "team01";
        final String name2 = "team02";
        final String name3 = "team03";
        final String name4 = "team04";

        Team team01 = new Team(name1);
        Team team02 = new Team(name2);
        Team team03 = new Team(name3);
        Team team04 = new Team(name4);

        Match match01 = new Match();
        Match match02 = new Match();

        Node right = new Node();
        Node left = new Node();
        Node parentNode = new Node();

        parentNode.setLeftNode(left);
        parentNode.setRightNode(right);

        left.setParentNode(parentNode);
        right.setParentNode(parentNode);

        left.setCurMatch(match01);
        right.setCurMatch(match02);

        match01.setTeam1(team01);
        match01.setTeam2(team02);
        match01.setPointsTeam1(3);
        match01.setPointsTeam2(1);

        match02.setTeam1(team03);
        match02.setTeam2(team04);
        match02.setPointsTeam1(1);
        match02.setPointsTeam2(3);

        parentNode.setCurMatch(new Match(match01.getWinningTeam(), match02.getWinningTeam()));
        parentNode.getCurMatch().setPointsTeam1(3);
        parentNode.getCurMatch().setPointsTeam2(4);

        assertThat(left.getCurMatch().getWinningTeam().getName()).isEqualTo("team01");
        assertThat(right.getCurMatch().getWinningTeam().getName()).isEqualTo("team04");
        assertThat(parentNode.getCurMatch().getWinningTeam().getName()).isEqualTo("team04");
    }

    @Test
    public void AutomaticChildNodeSet(){
        Phase phase1 = new Phase("FirstRound"); //Phasen haben noch keine Funktion
        Phase phase2 = new Phase("SecondRound");

        Node node01 = new Node();
        Node node02 = new Node();
        Node node03 = new Node();

        node01.setPhase(phase1);
        node02.setPhase(phase1);
        node03.setPhase(phase2);

        node01.setParentNode(node03);
        node02.setParentNode(node03);

        Match match01 = new Match(new Team("Team1"), new Team("Team2"));
        Match match02 = new Match(new Team("Team3"), new Team("Team4"));

        node01.setCurMatch(match01);
        node02.setCurMatch(match02);

        match01.setPointsTeam1(1);
        match01.setPointsTeam2(2);

        match02.setPointsTeam1(3);
        match02.setPointsTeam2(1);

        node03.setChildMatchWinners();

        assertThat(node03.getCurMatch().getTeam1().getName()).isEqualTo("Team2");
        assertThat(node03.getCurMatch().getTeam2().getName()).isEqualTo("Team3");
    }

    @Test
    public void testFileWriter01(){
        Filewriter filewriter = new Filewriter();
        filewriter.writeResult("team01", "team02" , new int[]{0,1});
        filewriter.writeResult("team03", "team04" , new int[]{3,1});
    }

    @Test
    public void testFileWriter02(){
        Filewriter filewriter = new Filewriter();

        Tournament tournament = new Tournament("Test-Tournament");

        Team team01 = new Team("team01");
        Team team02 = new Team("team02");
        Team team03 = new Team("team03");
        Team team04 = new Team("team04");
        Team team05 = new Team("team05");
        Team team06 = new Team("team06");
        Team team07 = new Team("team07");
        Team team08 = new Team("team08");

        Node node21 = new Node();

        Node node11 = new Node(node21);
        Node node12 = new Node(node21);

        Node node01 = new Node(node11);
        Node node02 = new Node(node11);
        Node node03 = new Node(node12);
        Node node04 = new Node(node12);

        Phase phase01 = new Phase(1);
        Phase phase02 = new Phase(2);
        Phase phase03 = new Phase(3);

        List<Phase> phases = new ArrayList<>();
        phases.add(phase01);
        phases.add(phase02);
        phases.add(phase03);

        tournament.setPhases(phases);

        node01.setPhase(phase03);
        node02.setPhase(phase03);
        node03.setPhase(phase03);
        node04.setPhase(phase03);

        node11.setPhase(phase02);
        node12.setPhase(phase02);

        node21.setPhase(phase01);


        node01.setCurMatch(new Match(team01, team02));
        node02.setCurMatch(new Match(team03, team04));
        node03.setCurMatch(new Match(team05, team06));
        node04.setCurMatch(new Match(team07, team08));

        node01.getCurMatch().setPointsTeam1(0);
        node01.getCurMatch().setPointsTeam2(1);

        node02.getCurMatch().setPointsTeam1(2);
        node02.getCurMatch().setPointsTeam2(1);

        node03.getCurMatch().setPointsTeam1(3);
        node03.getCurMatch().setPointsTeam2(0);

        node04.getCurMatch().setPointsTeam1(1);
        node04.getCurMatch().setPointsTeam2(2);

        node01.getParentNode().setChildMatchWinners();
        node02.getParentNode().setChildMatchWinners();
        node03.getParentNode().setChildMatchWinners();
        node04.getParentNode().setChildMatchWinners();

        node11.getCurMatch().setPointsTeam1(3);
        node11.getCurMatch().setPointsTeam2(2);

        node12.getCurMatch().setPointsTeam1(1);
        node12.getCurMatch().setPointsTeam1(2);

        filewriter.writeIntermediateResult(node11, tournament);
        filewriter.writeIntermediateResult(node12, tournament);

        node11.getParentNode().setChildMatchWinners();
        node12.getParentNode().setChildMatchWinners();

        node21.getCurMatch().setPointsTeam1(1);
        node21.getCurMatch().setPointsTeam2(0);

        filewriter.writeFinalResult(node21, tournament);

    }
}
