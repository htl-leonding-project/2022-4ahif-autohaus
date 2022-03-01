package at.htl;

import at.htl.comparator.TeamComparator;
import at.htl.entity.*;
import at.htl.filewriter.FilewriterGP;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class GruppenPhasenTest {

    Random random = new Random();
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

        match1.setTeam1Points(2);
        match1.setTeam2Points(3);
        match1.endMatch();
        match2.setTeam1Points(1);
        match2.setTeam2Points(3);
        match2.endMatch();
        match3.setTeam1Points(3);
        match3.setTeam2Points(2);
        match3.endMatch();
        match4.setTeam1Points(3);
        match4.setTeam2Points(1);
        match4.endMatch();
        match5.setTeam1Points(1);
        match5.setTeam2Points(2);
        match5.endMatch();
        match6.setTeam1Points(2);
        match6.setTeam2Points(1);
        match6.endMatch();
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

        match7.setTeam1Points(0);
        match7.setTeam2Points(3);
        match7.endMatch();
        match8.setTeam1Points(3);
        match8.setTeam2Points(1);
        match8.endMatch();
        match9.setTeam1Points(1);
        match9.setTeam2Points(0);
        match9.endMatch();
        match10.setTeam1Points(2);
        match10.setTeam2Points(3);
        match10.endMatch();
        match11.setTeam1Points(2);
        match11.setTeam2Points(1);
        match11.endMatch();
        match12.setTeam1Points(3);
        match12.setTeam2Points(2);
        match12.endMatch();

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

        //region Phase3
        /*phase3.addNode(new NodeGP(new MatchGP(team1, team5)));
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
        phase3.addNode(new NodeGP(new MatchGP(team4, team8)));*/
        //endregion

        tournament.addPhase(phase1);
        tournament.addPhase(phase2);
        //tournament.addPhase(phase3);

        group1.getTeams().sort(new TeamComparator());
        group2.getTeams().sort(new TeamComparator());

        for (TeamGP cur: group1.getTeams()) {
            System.out.println(cur.getName() + ": " + cur.getPoints());
        }
        System.out.println();
        for (TeamGP cur: group2.getTeams()) {
            System.out.println(cur.getName() + ": " + cur.getPoints());
        }
        System.out.println();

        for (TeamGP group1Team : group1.getTeams()){
            for(TeamGP group2Team : group2.getTeams()){
                MatchGP currentMatch = new MatchGP(group1Team, group2Team);
                currentMatch.setTeam1Points(random.nextInt()%5);
                currentMatch.setTeam2Points(random.nextInt()%5);
                currentMatch.endMatch();
                phase3.addNode(new NodeGP(currentMatch));
            }
        }

        List<TeamGP> winners = new ArrayList<TeamGP>();
        winners.addAll(group1.getTeams());
        winners.addAll(group2.getTeams());

        winners.sort(new TeamComparator());

        winningGroup.setTeams(winners.subList(0,4));

        for (TeamGP current:winningGroup.getTeams()) {
            System.out.println(current.getName() + ": " + current.getPoints());
        }

        FilewriterGP filewriterGP = new FilewriterGP();
        filewriterGP.writeResults(tournament);

    }
}
