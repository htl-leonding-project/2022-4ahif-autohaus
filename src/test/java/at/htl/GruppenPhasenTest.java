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

        Tournament tournament = new Tournament();
        List<Team> allTeams = new ArrayList<Team>();
        Group winningGroup = new Group();
        //region Gruppe 1

        //region Teams 1
        Team team1 = new Team("Portugal", "PRT");
        Team team2 = new Team("Serbien","SRB");
        Team team3 = new Team("Republik Irland","IRL");
        Team team4 = new Team("Luxemburg","LUX");
        //endregion

        //region Group 1
        List<Team> listofGroup1 = new ArrayList<>();
        listofGroup1.add(team1);
        listofGroup1.add(team2);
        listofGroup1.add(team3);
        listofGroup1.add(team4);
        Group group1 = new Group("Gruppe.A", listofGroup1);
        //endregion

        tournament.addGroup(group1);

        //region Matches
        Match match1 = new Match(team1,team2);
        Match match2 = new Match(team1,team3);
        Match match3 = new Match(team1,team4);
        Match match4 = new Match(team2,team3);
        Match match5 = new Match(team2,team4);
        Match match6 = new Match(team3,team4);

        match1.setPointsTeam1(2);
        match1.setPointsTeam2(3);
        match1.endMatch();
        match2.setPointsTeam1(1);
        match2.setPointsTeam2(3);
        match2.endMatch();
        match3.setPointsTeam1(3);
        match3.setPointsTeam2(2);
        match3.endMatch();
        match4.setPointsTeam1(3);
        match4.setPointsTeam2(1);
        match4.endMatch();
        match5.setPointsTeam1(1);
        match5.setPointsTeam2(2);
        match5.endMatch();
        match6.setPointsTeam1(2);
        match6.setPointsTeam2(1);
        match6.endMatch();
        //endregion

        //region List of Matches
        List<Match> matches1 = new ArrayList<>();
        matches1.add(match1);
        matches1.add(match2);
        matches1.add(match3);

        List<MatchGP> matches2 = new ArrayList<>();
        matches1.add(match4);
        matches1.add(match5);
        matches1.add(match6);
        //endregion

        //region Phasen
        Phase phase1 = new Phase("Gruppenspiele");
        Phase phase2 = new Phase("Gruppenspiele");
        Phase phase3 = new Phase("Gruppenspiele");
        //endregion

        //endregion

        //region Gruppe 2

        //region Teams 2
        Team team5 = new Team("Spanien","ESP");
        Team team6 = new Team("Schweden","SWE");
        Team team7 = new Team("Griechenland","GRC");
        Team team8 = new Team("Georgien","GEO");
        //endregion

        //region Group 1
        List<Team> listofGroup2 = new ArrayList<>();
        listofGroup2.add(team5);
        listofGroup2.add(team6);
        listofGroup2.add(team7);
        listofGroup2.add(team8);
        Group group2 = new Group("Gruppe.B", listofGroup2);
        //endregion

        tournament.addGroup(group2);

        //region Matches
        Match match7 = new Match(team5,team6);
        Match match8 = new Match(team5,team7);
        Match match9 = new Match(team5,team8);
        Match match10 = new Match(team6,team7);
        Match match11 = new Match(team6,team8);
        Match match12 = new Match(team7,team8);
        //endregion

        match7.setPointsTeam1(0);
        match7.setPointsTeam2(3);
        match7.endMatch();
        match8.setPointsTeam1(3);
        match8.setPointsTeam2(1);
        match8.endMatch();
        match9.setPointsTeam1(1);
        match9.setPointsTeam2(0);
        match9.endMatch();
        match10.setPointsTeam1(2);
        match10.setPointsTeam2(3);
        match10.endMatch();
        match11.setPointsTeam1(2);
        match11.setPointsTeam2(1);
        match11.endMatch();
        match12.setPointsTeam1(3);
        match12.setPointsTeam2(2);
        match12.endMatch();

        //region Phase1
        phase1.addNode(new Node(match1));
        phase1.addNode(new Node(match2));
        phase1.addNode(new Node(match3));
        phase1.addNode(new Node(match4));
        phase1.addNode(new Node(match5));
        phase1.addNode(new Node(match6));
        //endregion

        //region Phase2
        phase2.addNode(new Node(match7));
        phase2.addNode(new Node(match8));
        phase2.addNode(new Node(match9));
        phase2.addNode(new Node(match10));
        phase2.addNode(new Node(match11));
        phase2.addNode(new Node(match12));
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

        for (Team cur: group1.getTeams()) {
            System.out.println(cur.getName() + ": " + cur.getPoints());
        }
        System.out.println();
        for (Team cur: group2.getTeams()) {
            System.out.println(cur.getName() + ": " + cur.getPoints());
        }
        System.out.println();

        for (Team group1Team : group1.getTeams()){
            for(Team group2Team : group2.getTeams()){
                Match currentMatch = new Match(group1Team, group2Team);
                currentMatch.setPointsTeam1(random.nextInt()%5);
                currentMatch.setPointsTeam2(random.nextInt()%5);
                currentMatch.endMatch();
                phase3.addNode(new Node(currentMatch));
            }
        }

        List<Team> winners = new ArrayList<>();
        winners.addAll(group1.getTeams());
        winners.addAll(group2.getTeams());

        winners.sort(new TeamComparator());

        winningGroup.setTeams(winners.subList(0,4));

        for (Team current:winningGroup.getTeams()) {
            System.out.println(current.getName() + ": " + current.getPoints());
        }

        FilewriterGP filewriterGP = new FilewriterGP();
        filewriterGP.writeResults(tournament);

    }
}
