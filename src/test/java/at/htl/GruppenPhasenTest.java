package at.htl;

import at.htl.comparator.TeamComparator;
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

        List<TeamGP> allTeams = new ArrayList<TeamGP>();
        GruppeGP winningGroup = new GruppeGP();
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
        PhaseGP phase3 = new PhaseGP("Gruppenspiele");
        PhaseGP phase4 = new PhaseGP("Gruppenspiele");
        PhaseGP phase5 = new PhaseGP("Gruppenspiele");
        PhaseGP phase6 = new PhaseGP("Gruppenspiele");
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

        //region Phase1
        phase1.addNode(new NodeGP(){MatchGP m = match1;});
        phase1.addNode(new NodeGP(){MatchGP m = match2;});
        phase1.addNode(new NodeGP(){MatchGP m = match3;});
        phase1.addNode(new NodeGP(){MatchGP m = match4;});
        phase1.addNode(new NodeGP(){MatchGP m = match5;});
        phase1.addNode(new NodeGP(){MatchGP m = match6;});
        //endregion

        //region Phase2
        phase2.addNode(new NodeGP(){MatchGP m = match7;});
        phase2.addNode(new NodeGP(){MatchGP m = match8;});
        phase2.addNode(new NodeGP(){MatchGP m = match9;});
        phase2.addNode(new NodeGP(){MatchGP m = match10;});
        phase2.addNode(new NodeGP(){MatchGP m = match11;});
        phase2.addNode(new NodeGP(){MatchGP m = match12;});
        //endregion

        team1.setPoints(1);
        team2.setPoints(2);
        team3.setPoints(3);
        team4.setPoints(4);
        team5.setPoints(5);
        team6.setPoints(6);
        team7.setPoints(7);
        team8.setPoints(8);

        //region Phase3
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team1; TeamGP t2 = team5;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team1; TeamGP t2 = team6;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team1; TeamGP t2 = team7;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team1; TeamGP t2 = team8;};});
        //endregion

        //region Phase4
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team2; TeamGP t2 = team5;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team2; TeamGP t2 = team6;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team2; TeamGP t2 = team7;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team2; TeamGP t2 = team8;};});
        //endregion

        //region Phase5
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team3; TeamGP t2 = team5;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team3; TeamGP t2 = team6;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team3; TeamGP t2 = team7;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team3; TeamGP t2 = team8;};});
        //endregion

        //region Phase6
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team4; TeamGP t2 = team5;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team4; TeamGP t2 = team6;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team4; TeamGP t2 = team7;};});
        phase3.addNode(new NodeGP(){MatchGP m = new MatchGP(){TeamGP t1 = team4; TeamGP t2 = team8;};});
        //endregion

        allTeams.addAll(group2.getTeams());
        allTeams.addAll(group1.getTeams());

        allTeams.sort(new TeamComparator());

        winningGroup.setTeams(allTeams.subList(0,4));

        for (TeamGP cur: winningGroup.getTeams()) {
            System.out.println(cur.getName() + ": " + cur.getPoints());
        }

    }
}
