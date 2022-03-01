package at.htl.entity;

import javax.persistence.*;
import java.util.stream.Collectors;

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
    public NodeGP(MatchGP cur) {
        curMatch = cur;
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

    public String getMatchTable(){
        String returnString = String.format("""
                map Match.%svs%s {
                %s => %d
                %s => %d
                }
                """,this.getCurMatch().getTeam1().getAbbr(), this.getCurMatch().getTeam2().getAbbr(),
                this.getCurMatch().getTeam1().getName(), this.getCurMatch().getTeam1Points(),
                this.getCurMatch().getTeam2().getName(), this.getCurMatch().getTeam2Points());

        return returnString;
    }

    public String getMatchName(){
        String matchString = String.format("Match.%svs%s"
                , this.getCurMatch().getTeam1().getAbbr()
                , this.getCurMatch().getTeam2().getAbbr());
        return matchString;
    }
}
