package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Phase  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    String nameOfPhase;
    int level;

    @OneToMany
    List<Node> GPNodes = new ArrayList<>();

    public Phase() {
    }

    public Phase(int level) {
        this.level = level;
    }

    public Phase(String nameOfPhase) {
        this.nameOfPhase = nameOfPhase;
    }

    public Phase(String nameOfPhase, List<Node> GPNodes) {
        this.nameOfPhase = nameOfPhase;
        this.GPNodes = GPNodes;
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

    public List<Node> getGPNodes() {
        return GPNodes;
    }

    public void setGPNodes(List<Node> GPNodes) {
        this.GPNodes = GPNodes;
    }
}
