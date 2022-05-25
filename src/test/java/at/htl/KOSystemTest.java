package at.htl;

import at.htl.controller.TeamRepository;
import at.htl.controller.TournamentRepository;
import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
@Transactional
public class KOSystemTest {

    @Inject
    TournamentRepository TournamentRepo;
    @Inject
    TeamRepository TeamRepo;

    @Test
    public void Match_Test() {
        Tournament smallMatch = new Tournament("smallMatch");

        Phase phaseOne = new Phase(1);
        Phase phaseTwo = new Phase(2);

        Node nodeOne = new Node(new Match(TeamRepo.findById(1L), TeamRepo.findById(2L)));
        Node nodeTwo = new Node(new Match(TeamRepo.findById(3L), TeamRepo.findById(4L)));

        nodeOne.getCurMatch().setPointsTeam1(2);
        nodeOne.getCurMatch().setPointsTeam2(1);

        nodeTwo.getCurMatch().setPointsTeam1(0);
        nodeTwo.getCurMatch().setPointsTeam2(2);

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

        nodeThree.setLeftNode(nodeOne);
        nodeThree.setRightNode(nodeTwo);

        TournamentRepo.persist(smallMatch);

        Filewriter filewriter = new Filewriter();

        filewriter.writeFinalResult(nodeThree, smallMatch);
    }


}
