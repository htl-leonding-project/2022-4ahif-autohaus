package at.htl;

import at.htl.comparator.TeamComparator;
import at.htl.entity.*;
import at.htl.filewriter.FilewriterGP;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class GruppenPhasenTest {

    @Test
    public void getWinningTeamTest(){

        TournamentGP tournament = new TournamentGP();
        List<TeamGP> allTeams = new ArrayList<TeamGP>();
        GroupGP winningGroup = new GroupGP();
        //region Gruppe 1

        //region Teams 1
        TeamGP team1 = new TeamGP("Portugal", "PRT",0);
        TeamGP team2 = new TeamGP("Serbien","SRB",0);
        TeamGP team3 = new TeamGP("Republik Irland","IRL",0);
        TeamGP team4 = new TeamGP("Luxemburg","LUX",0);
        //endregion

        //region Group 1
        List<TeamGP> listofGroup1 = new ArrayList<>();
        listofGroup1.add(team1);
        listofGroup1.add(team2);
        listofGroup1.add(team3);
        listofGroup1.add(team4);
        GroupGP group1 = new GroupGP("Gruppe.A", listofGroup1);
        //endregion

        tournament.addGroup(group1);

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
        //endregion

        //endregion

        //region Gruppe 2

        //region Teams 2
        TeamGP team5 = new TeamGP("Spanien","ESP",0);
        TeamGP team6 = new TeamGP("Schweden","SWE",0);
        TeamGP team7 = new TeamGP("Griechenland","GRC",0);
        TeamGP team8 = new TeamGP("Georgien","GEO",0);
        //endregion

        //region Group 1
        List<TeamGP> listofGroup2 = new ArrayList<>();
        listofGroup2.add(team5);
        listofGroup2.add(team6);
        listofGroup2.add(team7);
        listofGroup2.add(team8);
        GroupGP group2 = new GroupGP("Gruppe.B", listofGroup2);
        //endregion

        tournament.addGroup(group2);

        //region Matches
        MatchGP match7 = new MatchGP(team5,team6);
        MatchGP match8 = new MatchGP(team5,team7);
        MatchGP match9 = new MatchGP(team5,team8);
        MatchGP match10 = new MatchGP(team6,team7);
        MatchGP match11 = new MatchGP(team6,team8);
        MatchGP match12 = new MatchGP(team7,team8);
        //endregion

        //region Phase1
        phase1.addNode(new NodeGP(match1));
        phase1.addNode(new NodeGP(match2));
        phase1.addNode(new NodeGP(match3));
        phase1.addNode(new NodeGP(match4));
        phase1.addNode(new NodeGP(match5));
        phase1.addNode(new NodeGP(match6));
        //endregion

        //region Phase2
        phase2.addNode(new NodeGP(match7));
        phase2.addNode(new NodeGP(match8));
        phase2.addNode(new NodeGP(match9));
        phase2.addNode(new NodeGP(match10));
        phase2.addNode(new NodeGP(match11));
        phase2.addNode(new NodeGP(match12));
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
        phase3.addNode(new NodeGP(new MatchGP(team1, team5)));
        phase3.addNode(new NodeGP(new MatchGP(team1, team6)));
        phase3.addNode(new NodeGP(new MatchGP(team1, team7)));
        phase3.addNode(new NodeGP(new MatchGP(team1, team8)));

        phase3.addNode(new NodeGP(new MatchGP(team2, team5)));
        phase3.addNode(new NodeGP(new MatchGP(team2, team6)));
        phase3.addNode(new NodeGP(new MatchGP(team2, team7)));
        phase3.addNode(new NodeGP(new MatchGP(team2, team8)));

        phase3.addNode(new NodeGP(new MatchGP(team3, team5)));
        phase3.addNode(new NodeGP(new MatchGP(team3, team6)));
        phase3.addNode(new NodeGP(new MatchGP(team3, team7)));
        phase3.addNode(new NodeGP(new MatchGP(team3, team8)));

        phase3.addNode(new NodeGP(new MatchGP(team4, team5)));
        phase3.addNode(new NodeGP(new MatchGP(team4, team6)));
        phase3.addNode(new NodeGP(new MatchGP(team4, team7)));
        phase3.addNode(new NodeGP(new MatchGP(team4, team8)));
        //endregion

        tournament.addPhase(phase1);
        tournament.addPhase(phase2);
        //tournament.addPhase(phase3);


        allTeams.addAll(group2.getTeams());
        allTeams.addAll(group1.getTeams());

        allTeams.sort(new TeamComparator());

        winningGroup.setTeams(allTeams.subList(0,4));

        for (TeamGP cur: winningGroup.getTeams()) {
            System.out.println(cur.getName() + ": " + cur.getPoints());
        }

        FilewriterGP filewriterGP = new FilewriterGP();
        filewriterGP.writeResults(tournament);

    }
}
