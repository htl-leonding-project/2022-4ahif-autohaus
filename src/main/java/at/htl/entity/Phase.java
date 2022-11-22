package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_PHASE")
public class Phase  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "P_NAME")
    String nameOfPhase;
    @Column(name="P_LEVEL")
    int level;

    public Phase() {
    }

    public Phase(int level) {
        this.level = level;
    }

    public Phase(String nameOfPhase) {
        this.nameOfPhase = nameOfPhase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Phase level "+level;
    }
}
