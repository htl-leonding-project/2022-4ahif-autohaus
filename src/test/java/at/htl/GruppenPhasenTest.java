package at.htl;

import at.htl.entity.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class GruppenPhasenTest {

    @Test
    public void getWinningTeamTest(){

        //region Gruppe 1

        //region Teams 1
        TeamGP team1 = new TeamGP("Portugal",0);
        TeamGP team2 = new TeamGP("Serbien",0);
        TeamGP team3 = new TeamGP("Republik Irland",0);
        TeamGP team4 = new TeamGP("Luxemburg",0);
        //endregion

        //region Group 1
        List<TeamGP> listofGroup1 = new ArrayList<>();
        listofGroup1.add(team1);
        listofGroup1.add(team2);
        listofGroup1.add(team3);
        listofGroup1.add(team4);
        GruppeGP group1 = new GruppeGP("Gruppe A", listofGroup1);
        //endregion

        //region Matches
        MatchGP match1 = new MatchGP(team1,team2);
        MatchGP match2 = new MatchGP(team1,team3);
        MatchGP match3 = new MatchGP(team1,team4);
        MatchGP match4 = new MatchGP(team2,team3);
        MatchGP match5 = new MatchGP(team2,team4);
        MatchGP match6 = new MatchGP(team3,team4);
        //endregion

        //region List of Matches
        List<MatchGP> matches1 = new ArrayList<>();
        matches1.add(match1);
        matches1.add(match2);
        matches1.add(match3);

        List<MatchGP> matches2 = new ArrayList<>();
        matches1.add(match4);
        matches1.add(match5);
        matches1.add(match6);
        //endregion

        //region Phasen
        PhaseGP phase1 = new PhaseGP("Gruppenspiele");
        PhaseGP phase2 = new PhaseGP("Gruppenspiele");
        //endregion

        //endregion

        //region Gruppe 2

        //region Teams 2
        TeamGP team5 = new TeamGP("Spanien",0);
        TeamGP team6 = new TeamGP("Schweden",0);
        TeamGP team7 = new TeamGP("Griechenland",0);
        TeamGP team8 = new TeamGP("Georgien",0);
        //endregion

        //region Group 1
        List<TeamGP> listofGroup2 = new ArrayList<>();
        listofGroup2.add(team5);
        listofGroup2.add(team6);
        listofGroup2.add(team7);
        listofGroup2.add(team8);
        GruppeGP group2 = new GruppeGP("Gruppe A", listofGroup2);
        //endregion

        //region Matches
        MatchGP match7 = new MatchGP(team5,team6);
        MatchGP match8 = new MatchGP(team5,team7);
        MatchGP match9 = new MatchGP(team5,team8);
        MatchGP match10 = new MatchGP(team6,team7);
        MatchGP match11 = new MatchGP(team6,team8);
        MatchGP match12 = new MatchGP(team7,team8);
        //endregion

        //region List of Matches
        List<MatchGP> matches3 = new ArrayList<>();
        matches3.add(match7);
        matches3.add(match8);
        matches3.add(match9);

        List<MatchGP> matches4 = new ArrayList<>();
        matches4.add(match10);
        matches4.add(match11);
        matches4.add(match12);
        //endregion

        //region Phasen
        PhaseGP phase3 = new PhaseGP("Gruppenspiele");
        PhaseGP phase4 = new PhaseGP("Gruppenspiele");
        //endregion

        //endregion

        //region setPoints

        team1.setPoints(1);
        team2.setPoints(2);
        team3.setPoints(3);
        team4.setPoints(4);
        team5.setPoints(5);
        team6.setPoints(6);
        team7.setPoints(7);
        team8.setPoints(8);

        assertThat(match1.getWinningTeam().getName()).isEqualTo(listofGroup2);

        //endregion

        //region NodesG1
        NodeGP topG1 = new NodeGP();
        topG1.setLeftNode(new NodeGP(){MatchGP m = match1;});
        topG1.setRightNode(new NodeGP(){MatchGP m = match2;});
        topG1.setCenterNode(new NodeGP(){MatchGP m = match3;});

        NodeGP bottomG1 = new NodeGP();
        bottomG1.setLeftNode(new NodeGP(){MatchGP m = match4;});
        bottomG1.setRightNode(new NodeGP(){MatchGP m = match5;});
        bottomG1.setCenterNode(new NodeGP(){MatchGP m = match6;});
        //endregion

        //region NodesG2
        NodeGP topG2 = new NodeGP();
        topG2.setLeftNode(new NodeGP(){MatchGP m = match7;});
        topG2.setRightNode(new NodeGP(){MatchGP m = match8;});
        topG2.setCenterNode(new NodeGP(){MatchGP m = match9;});

        NodeGP bottomG2 = new NodeGP();
        bottomG2.setLeftNode(new NodeGP(){MatchGP m = match10;});
        bottomG2.setRightNode(new NodeGP(){MatchGP m = match11;});
        bottomG2.setCenterNode(new NodeGP(){MatchGP m = match12;});
        //endregion

        //region NodeP1
        NodeGP nodeP1 = new NodeGP();
        nodeP1.setCurMatch(new MatchGP(team1,team5));
        nodeP1.setCurMatch(new MatchGP(team1,team6));
        nodeP1.setCurMatch(new MatchGP(team1,team7));
        nodeP1.setCurMatch(new MatchGP(team1,team8));
        //endregion

        //region NodeP2
        NodeGP nodeP2 = new NodeGP();
        nodeP2.setCurMatch(new MatchGP(team2,team5));
        nodeP2.setCurMatch(new MatchGP(team2,team6));
        nodeP2.setCurMatch(new MatchGP(team2,team7));
        nodeP2.setCurMatch(new MatchGP(team2,team8));
        //endregion

    }
}
