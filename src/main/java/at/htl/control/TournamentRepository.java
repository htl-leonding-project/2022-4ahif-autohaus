package at.htl.control;

import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {
    @Inject
    MatchRepository matchRepository;

    @Inject
    Logger log;

    @Inject
    NodeRepository nodeRepository;

    @Transactional
    public List<Node> setUpTournament (String name, List<Team> teams) {
        Tournament tournament = new Tournament(name);
        List<Match> matches = matchRepository.matchTeams(teams, tournament);

        if (teams.size() == 4) {
            tournament.setFinalNode(buildSmallNodeTree(matches));
        } else if (teams.size() == 8){
            tournament.setFinalNode(buildMediumNodeTree(matches));
        } else if (teams.size() == 16){
            tournament.setFinalNode(buildLargeNodeTree(matches));
        }

        matchRepository.persist(matches);
        this.persist(tournament);

        return nodeRepository.getNodesAsList(tournament.getFinalNode());
    }


    private Node buildSmallNodeTree(List<Match> matches) {

        Node node1 = new Node(matches.get(0));
        Node node2 = new Node(matches.get(1));

        Node node3 = new Node();
        node1.setParentNode(node3);
        node2.setParentNode(node3);

        Phase phase1 = new Phase(2);
        Phase phase2 = new Phase(1);

        node1.setPhase(phase1);
        node2.setPhase(phase1);
        node3.setPhase(phase2);

        return node3;
    }

    private Node buildMediumNodeTree(List<Match> matches) {

        Node node1 = new Node(matches.get(0));
        Node node2 = new Node(matches.get(1));
        Node node3 = new Node(matches.get(2));
        Node node4 = new Node(matches.get(3));

        Node node5 = new Node();
        Node node6 = new Node();

        Node node7 = new Node();

        node1.setParentNode(node5);
        node2.setParentNode(node5);
        node3.setParentNode(node6);
        node4.setParentNode(node6);

        node5.setParentNode(node7);
        node6.setParentNode(node7);

        Phase phase1 = new Phase(3);
        Phase phase2 = new Phase(2);
        Phase phase3 = new Phase(1);

        node1.setPhase(phase1);
        node2.setPhase(phase1);
        node3.setPhase(phase1);
        node4.setPhase(phase1);

        node5.setPhase(phase2);
        node6.setPhase(phase2);

        node7.setPhase(phase3);

        return node7;
    }

    private Node buildLargeNodeTree(List<Match> matches) {

        Node node1 = new Node(matches.get(0));
        Node node2 = new Node(matches.get(1));
        Node node3 = new Node(matches.get(2));
        Node node4 = new Node(matches.get(3));
        Node node5 = new Node(matches.get(4));
        Node node6 = new Node(matches.get(5));
        Node node7 = new Node(matches.get(6));
        Node node8 = new Node(matches.get(7));

        Node node9 = new Node();
        Node node10 = new Node();
        Node node11 = new Node();
        Node node12 = new Node();

        Node node13 = new Node();
        Node node14 = new Node();

        Node node15 = new Node();

        node1.setParentNode(node9);
        node2.setParentNode(node9);
        node3.setParentNode(node10);
        node4.setParentNode(node10);
        node5.setParentNode(node11);
        node6.setParentNode(node11);
        node7.setParentNode(node12);
        node8.setParentNode(node12);

        node9.setParentNode(node13);
        node10.setParentNode(node13);
        node11.setParentNode(node14);
        node12.setParentNode(node14);

        node13.setParentNode(node15);
        node14.setParentNode(node15);

        Phase phase1 = new Phase(4);
        Phase phase2 = new Phase(3);
        Phase phase3 = new Phase(2);
        Phase phase4 = new Phase(1);

        node1.setPhase(phase1);
        node2.setPhase(phase1);
        node3.setPhase(phase1);
        node4.setPhase(phase1);
        node5.setPhase(phase1);
        node6.setPhase(phase1);
        node7.setPhase(phase1);
        node8.setPhase(phase1);

        node9.setPhase(phase2);
        node10.setPhase(phase2);
        node11.setPhase(phase2);
        node12.setPhase(phase2);

        node13.setPhase(phase3);
        node14.setPhase(phase3);

        node15.setPhase(phase4);

        return node15;
    }

    public Tournament findByName(String name) {
        return find("name", name).firstResult();
    }

    public void save(Tournament tournament){
        if(this.findByName(tournament.getName()) == null){
            this.persist(tournament);
        }
        else {
            throw new PersistenceException();
        }
    }
}
