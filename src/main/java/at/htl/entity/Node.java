package at.htl.entity;

import javax.persistence.*;

@Entity
public class Node {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long nodeID;
    @ManyToOne
    Node rightNode;
    @ManyToOne
    Node leftNode;
    @ManyToOne
    Node parentNode;
    @ManyToOne
    Match match;
    @ManyToOne
    Phase phase;
    //endregion

    //region Constructor
    public Node(Node rightNode, Node leftNode, Node parentNode, Match match, Phase phase) {
        this.rightNode = rightNode;
        this.leftNode = leftNode;
        this.parentNode = parentNode;
        this.match = match;
        this.phase = phase;
    }

    public Node() {
    }
    //endregion

    //region Getter and Setter
    public Long getNodeID() {
        return nodeID;
    }

    public void setNodeID(Long nodeID) {
        this.nodeID = nodeID;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    //endregion
}
