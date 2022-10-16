package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "T_NODE")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    Node leftNode;
    @ManyToOne(cascade = CascadeType.ALL)
    Node rightNode;
    @ManyToOne
    Node parentNode;
    @Transient
    Node centerNode;
    @ManyToOne(cascade = CascadeType.ALL)
    Match curMatch;
    @ManyToOne(cascade = CascadeType.ALL)
    Phase phase;

    public Node() {
    }

    public Node(Node parentNode) {
        setParentNode(parentNode);
    }

    public Node(Match match) {this.curMatch = match;}

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
        leftNode.parentNode = this;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
        rightNode.parentNode = this;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public Node getCenterNode() {
        return centerNode;
    }

    public void setCenterNode(Node centerNode) {
        this.centerNode = centerNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
        if(this.parentNode.getLeftNode() == null)
            this.parentNode.setLeftNode(this);
        else
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

    public String getMatchTableGP(){
        String returnString = String.format("""
                map Match.%svs%s {
                %s => %d
                %s => %d
                }
                """,this.getCurMatch().getTeam1().getAbbr(), this.getCurMatch().getTeam2().getAbbr(),
                this.getCurMatch().getTeam1().getName(), this.getCurMatch().getPointsTeam1(),
                this.getCurMatch().getTeam2().getName(), this.getCurMatch().getPointsTeam2());

        return returnString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return curMatch + " in "+phase;
    }

    public boolean areChildrenComplete(){
        if(this.leftNode != null && this.rightNode!=null) {
            if (this.leftNode.getCurMatch().pointsTeam1 != this.leftNode.getCurMatch().pointsTeam2
                    && this.rightNode.getCurMatch().pointsTeam1 != this.rightNode.getCurMatch().pointsTeam2) {
                return true;
            }
            return false;
        }
        return true;
    }
}
