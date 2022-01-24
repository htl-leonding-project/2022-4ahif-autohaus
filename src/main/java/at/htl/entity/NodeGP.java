package at.htl.entity;

import javax.persistence.*;

@Entity
public class NodeGP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    NodeGP leftNode;
    @ManyToOne
    NodeGP rightNode;
    @ManyToOne
    NodeGP parentNode;
    @ManyToOne
    NodeGP centerNode;
    @ManyToOne
    MatchGP curMatch;
    @ManyToOne
    PhaseGP phase;

    public NodeGP() {
    }


    public PhaseGP getPhase() {
        return phase;
    }

    public NodeGP getCenterNode() {
        return centerNode;
    }

    public void setCenterNode(NodeGP centerNode) {
        this.centerNode = centerNode;
    }

    public void setPhase(PhaseGP phase) {
        this.phase = phase;
    }

    public NodeGP getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeGP leftNode) {
        this.leftNode = leftNode;
    }

    public NodeGP getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeGP rightNode) {
        this.rightNode = rightNode;
    }

    public NodeGP getParentNode() {
        return parentNode;
    }

    public void setParentNode(NodeGP parentNode) {
        this.parentNode = parentNode;
        if(this.parentNode.getLeftNode() == null)
            this.parentNode.setLeftNode(this);
        else if(this.parentNode.getRightNode() == null)
            this.parentNode.setRightNode(this);
    }

    public MatchGP getCurMatch() {
        return curMatch;
    }

    public void setCurMatch(MatchGP curMatch) {
        this.curMatch = curMatch;
    }

    public void setChildMatchWinners() {
        setCurMatch(new MatchGP(this.leftNode.getCurMatch().getWinningTeam(),
                this.rightNode.getCurMatch().getWinningTeam()));
    }
}
