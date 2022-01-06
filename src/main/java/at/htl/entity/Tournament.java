package at.htl.entity;

import javax.persistence.Column;
import java.util.List;

public class Tournament {

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
