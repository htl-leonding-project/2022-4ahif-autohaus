package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Node leftNode;
    @ManyToOne
    Node rightNode;
    @ManyToOne
    Node parentNode;
//    Node centerNode;  //nur bei "kleinen Finale"
    @ManyToOne
    Match curMatch;
    @ManyToOne
    Phase phase;

    public Node() {
    }

    public Node(Node parentNode) {
        setParentNode(parentNode);
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
        if(this.parentNode.getLeftNode() == null)
            this.parentNode.setLeftNode(this);
        else if(this.parentNode.getRightNode() == null)
            this.parentNode.setRightNode(this);
    }

    public Match getCurMatch() {
        return curMatch;
    }

    public void setCurMatch(Match curMatch) {
        this.curMatch = curMatch;
    }

    public void setChildMatchWinners() {
        setCurMatch(new Match(this.leftNode.getCurMatch().getWinningTeam(),
                this.rightNode.getCurMatch().getWinningTeam()));
    }
}
