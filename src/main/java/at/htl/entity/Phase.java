package at.htl.entity;

public class Phase {

    //region fields
    Long phasenID;
    String phasenName;
    Node node;
    Competition competition;
    //endregion

    //region Constructor
    public Phase(Long phasenID, String phasenName, Node node, Competition competition) {
        this.phasenID = phasenID;
        this.phasenName = phasenName;
        this.node = node;
        this.competition = competition;
    }

    public Phase() {
    }
    //endregion

    //region Getter and Setter
    public Long getPhasenID() {
        return phasenID;
    }

    public void setPhasenID(Long phasenID) {
        this.phasenID = phasenID;
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
