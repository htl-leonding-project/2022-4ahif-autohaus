package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;

public class Phase extends PanacheEntity {

    @Column(length = 30)
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
