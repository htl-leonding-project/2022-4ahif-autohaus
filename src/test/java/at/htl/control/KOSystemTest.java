package at.htl.control;

import at.htl.control.MatchRepository;
import at.htl.control.TeamRepository;
import at.htl.control.TournamentRepository;
import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Transactional
public class KOSystemTest {

    @Inject
    TournamentRepository TournamentRepo;
    @Inject
    TeamRepository TeamRepo;
    @Inject
    MatchRepository MatchRepo;

    @Test
    public void Match_Test() {

        Tournament smallMatch = new Tournament("smallMatch");

        Phase phaseOne = new Phase(1);
        Phase phaseTwo = new Phase(2);

        List<Team> teams = TeamRepo.setTeamsForTournament(4);

        Node nodeOne = new Node(new Match(teams.get(0), teams.get(1)));
        Node nodeTwo = new Node(new Match(teams.get(2), teams.get(3)));

        nodeOne.getCurMatch().setPointsTeam1(2);
        nodeOne.getCurMatch().setPointsTeam2(1);
        MatchRepo.persist(nodeOne.getCurMatch());

        nodeTwo.getCurMatch().setPointsTeam1(0);
        nodeTwo.getCurMatch().setPointsTeam2(2);
        MatchRepo.persist(nodeTwo.getCurMatch());

        Node nodeThree = new Node(new Match(
                nodeOne.getCurMatch().getWinningTeam(),
                nodeTwo.getCurMatch().getWinningTeam()
        ));

        smallMatch.addPhase(phaseOne);
        smallMatch.addPhase(phaseTwo);

        phaseTwo.addNode(nodeOne);
        phaseTwo.addNode(nodeTwo);

        phaseOne.addNode(nodeThree);

        nodeThree.getCurMatch().setPointsTeam1(0);
        nodeThree.getCurMatch().setPointsTeam2(1);
        MatchRepo.persist(nodeThree.getCurMatch());

        nodeThree.setLeftNode(nodeOne);
        nodeThree.setRightNode(nodeTwo);

        TeamRepo.persist(teams);
        TournamentRepo.persist(smallMatch);

        Filewriter filewriter = new Filewriter();

        filewriter.writeFinalResult(nodeThree, smallMatch);

        assertEquals(TeamRepo.findById(4L).getWinAmount(), 2);
    }

    @Test
    public void TestTreeBuilding(){
        Tournament t1 = new Tournament("treeSmall");
        List<Team> teams = TeamRepo.setTeamsForTournament(4);
        List<Node> finalNode = TournamentRepo.setUpTournament(teams);

        Filewriter newFile = new Filewriter();
        newFile.writeFinalResult(finalNode.get(finalNode.size()-1), t1);
    }
}
