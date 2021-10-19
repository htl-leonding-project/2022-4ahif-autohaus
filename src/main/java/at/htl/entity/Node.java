package at.htl.entity;

public class Node {

    //region fields
    Long nodeID;
    Node rightNode;
    Node leftNode;
    Node parentNode;
    Match match;
    Phase phase;
    //endregion

    //region Constructor
    public Node(Long nodeID, Node rightNode, Node leftNode, Node parentNode, Match match, Phase phase) {
        this.nodeID = nodeID;
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
