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

    public Tournament setUpTournament (String name, List<Team> teams) {
        Tournament tournament = new Tournament(name);
        List<Match> matches = matchRepository.matchTeams(teams);

        if (teams.size() == 4) {
            buildSmallNodeTree(matches);
        }
        return tournament;
    }


    private void buildSmallNodeTree(List<Match> matches) {

        Node node1 = new Node(matches.get(0));
        Node node2 = new Node(matches.get(1));

        Node node3 = new Node();
        node1.setParentNode(node3);
        node2.setParentNode(node3);

        Phase phase1 = new Phase(1);
        Phase phase2 = new Phase(2);

        node1.setPhase(phase1);
        node2.setPhase(phase1);
        node3.setPhase(phase2);

        List<Node> nodes = new LinkedList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
    }

    public Tournament findByName(String name) {
        return find("name", name).firstResult();
    }
}
