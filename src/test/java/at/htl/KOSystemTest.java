package at.htl;

import at.htl.controller.TeamRepository;
import at.htl.controller.TournamentRepository;
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

    @Test
    public void Match_Test() {
        Tournament smallMatch = new Tournament("smallMatch");

        Phase phaseOne = new Phase(1);
        Phase phaseTwo = new Phase(2);

        List<Team> teams = new ArrayList<>();
        for(int i=1; i<5; i++){
            teams.add(TeamRepo.findById((long)i));
        }

        Node nodeOne = new Node(new Match(teams.get(0), teams.get(1)));
        Node nodeTwo = new Node(new Match(teams.get(2), teams.get(3)));

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

        TeamRepo.persist(teams);
        TournamentRepo.persist(smallMatch);

        Filewriter filewriter = new Filewriter();

        filewriter.writeFinalResult(nodeThree, smallMatch);

        assertEquals(TeamRepo.findById(4L).getWinAmount(), 2);
    }
}
