package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Phase {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long phaseID;
    String phasenName;
    Node node;
    Competition competition;
    //endregion

    //region Constructor
    public Phase(String phasenName, Node node, Competition competition) {
        this.phasenName = phasenName;
        this.node = node;
        this.competition = competition;
    }

    public Phase() {
    }
    //endregion

    //region Getter and Setter
    public Long getPhasenID() {
        return phaseID;
    }

    public void setPhasenID(Long phasenID) {
        this.phaseID = phasenID;
    }

    public String getPhasenName() {
        return phasenName;
    }

    public void setPhasenName(String phasenName) {
        this.phasenName = phasenName;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
    //endregion
}
