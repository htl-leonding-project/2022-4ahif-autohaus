package at.htl.entity;

public class Phase {

    String nameOfPhase;
    int level;

    public Phase() {
    }

    public Phase(int level) {
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
