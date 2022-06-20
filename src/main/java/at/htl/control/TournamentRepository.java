package at.htl.control;

import at.htl.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class TournamentRepository implements PanacheRepository<Tournament> {
    @Inject
    MatchRepository matchRepository;

    public Node setUpTournament (List<Team> teams) {
        List<Match> matches = matchRepository.matchTeams(teams);
        List<Node> nodes = new LinkedList<>();

        if (teams.size() == 4) {
            nodes = buildSmallNodeTree(matches);
        } else if (teams.size() == 8){
            nodes = buildMediumNodeTree(matches);
        }
        return nodes.get(nodes.size()-1);
    }


    private List<Node> buildSmallNodeTree(List<Match> matches) {

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

        List<Node> nodes = new LinkedList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        return nodes;
    }

    private List<Node> buildMediumNodeTree(List<Match> matches) {

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

        List<Node> nodes = new LinkedList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        nodes.add(node7);

        return nodes;
    }

    public Tournament findByName(String name) {
        return find("name", name).firstResult();
    }
}
