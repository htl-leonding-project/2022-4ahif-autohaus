package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import java.util.List;

public class Tournament extends PanacheEntity {

    //erst einmal leer lassen
    @Column(length = 20)
    String name;

    List<Phase>phases;

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
