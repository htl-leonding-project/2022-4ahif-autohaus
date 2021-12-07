package prototype.entity;

import java.util.List;

public class Phase {

    Tournament tournament;
    String nameOfPhase;

    public Phase() {
    }

    public Phase(String nameOfPhase) {
        this.nameOfPhase = nameOfPhase;
    }

    public String getNameOfPhase() {
        return nameOfPhase;
    }

    public void setNameOfPhase(String nameOfPhase) {
        this.nameOfPhase = nameOfPhase;
    }
}
