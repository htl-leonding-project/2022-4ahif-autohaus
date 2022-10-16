package at.htl.control;

import at.htl.entity.Match;
import at.htl.entity.Node;
import at.htl.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class NodeRepository implements PanacheRepository<Node> {

    public List<Node> getNodesAsList(Node finalNode){
        List<Node> newList = new LinkedList<>();

        return goToNode(newList, finalNode);
    }

    public List<Node> goToNode(List<Node> nodes, Node node){

        List<Node> nodes1 = nodes;

        if(node.getLeftNode() != null){
            nodes1 = goToNode(nodes1,node.getLeftNode());
        }
        if(node.getRightNode() != null){
            nodes1 = goToNode(nodes1, node.getRightNode());
        }

        nodes1.add(node);
        return nodes;

    }

    public Node getNodeByMatch(Match match){
        return this.find("curmatch_m_id", match.getId()).firstResult();
    }
}
