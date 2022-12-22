package at.htl.control;

import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Comparator;
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

    @Inject
    TeamRepository teamRepository;

    public List<Tournament> getAllSorted(){
        return this.findAll().stream().sorted(Comparator.comparing(Tournament::getStartDate).reversed()).toList();
    }

    @Transactional
    public List<Node> setUpTournament (String name, List<Team> teams) {
        Tournament tournament = new Tournament(name);

        if (teams.size() == 4) {
            tournament.setFinalNode(buildSmallNodeTree());
        } else if (teams.size() == 8){
            tournament.setFinalNode(buildMediumNodeTree());
        } else if (teams.size() == 16){
            tournament.setFinalNode(buildLargeNodeTree());
        }
        tournament.setStatus(Status.IN_PREPARATION);
        this.persist(tournament);

        for (Team team:teams) {
            teamRepository.find("tournamentId=?1 and abbr=?2", -1L, team.getAbbr())
                    .firstResult()
                    .setTournamentId(tournament.getId());
        }
        return nodeRepository.getNodesAsList(tournament.getFinalNode());
    }

    @Transactional
    public void generateMatches (String name, List<Team> teams){
        Tournament tournament = this.findByName(name);

        tournament.setStatus(Status.READY);

        List<Node> nodes = nodeRepository.getNodesAsList(tournament.getFinalNode());

        for (int i = 0; i < teams.size(); i+=2) {
            nodes.get(i/2).setCurMatch(new Match(teams.get(i), teams.get(i+1)));
        }
    }


    private Node buildSmallNodeTree() {

        Node node1 = new Node();
        Node node2 = new Node();

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

    private Node buildMediumNodeTree() {

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();

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

    private Node buildLargeNodeTree() {

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        Node node7 = new Node();
        Node node8 = new Node();

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

    public List<MatchDTO> getMatchesDto(Tournament t) {
        List<Node> nodes = nodeRepository.getNodesAsList(t.getFinalNode());
        List<MatchDTO> matches = new LinkedList<>();
        for (Node node: nodes) {
            if(node.getCurMatch() != null) {
                matches.add(new MatchDTO(node.getCurMatch(), node.getPhase()));
            }
        }
        return matches;
    }

    public String generateTreeJson(Tournament t){
        List<Node> nodes = nodeRepository.getNodesAsList(t.getFinalNode());
        int currentLevel = nodes.get(0).getPhase().getLevel();
        String json = """
                {rounds: [
                            {
                                type: 'Winnerbracket',
                                matches: [
                """;

        nodes.sort(Comparator.comparing(Node::getPhaseLevel).reversed());

        for (Node node:nodes) {
            if(node.getPhase().getLevel() == currentLevel){
                if(nodes.get(0) != node)
                    json += ",";
            } else {
                json += """
                            ]
                        },""";
                if(node.getPhase().getLevel() == 1){
                    json += """
                            {
                                            type: 'Final',
                                            matches: [
                                      """;
                } else {
                    json += """
                            {
                                            type: 'Winnerbracket',
                                            matches: [
                            """;
                }
                currentLevel = node.getPhase().getLevel();
            }
            json += String.format("""
                                    {
                                        teams: [
                                            { name: '%s', score: %s},
                                            { name: '%s', score: %s}
                                        ]
                                    }""",
                    (node.getCurMatch() == null ? " " : node.getCurMatch().getTeam1().getName() ),
                    (node.getCurMatch() == null ? "0" : node.getCurMatch().pointsTeam1),
                    (node.getCurMatch() == null ? " " : node.getCurMatch().getTeam2().getName() ),
                    (node.getCurMatch() == null ? "0" : node.getCurMatch().pointsTeam2));
        }

        json += """
                    ]
                    }]}""";

        return json;
    }

    public void deleteTournamentById(Long aLong) {

        for (Team team: teamRepository.getByTournamentId(aLong)) {
            teamRepository.deleteById(team.getId());
        }

        this.deleteById(aLong);
    }
}
