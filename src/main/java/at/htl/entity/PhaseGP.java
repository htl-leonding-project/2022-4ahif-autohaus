package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PhaseGP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phasenName;
    @OneToMany
    List<NodeGP> nodes = new ArrayList<NodeGP>();

    //region Constructor


    public PhaseGP(String phasenName) {
        this.phasenName = phasenName;
    }

    public PhaseGP(String phasenName, List<NodeGP> nodes) {
        this.phasenName = phasenName;
        this.nodes = nodes;
    }

    public PhaseGP() {
    }

    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhasenName() {
        return phasenName;
    }

    public void setPhasenName(String phasenName) {
        this.phasenName = phasenName;
    }

    public void addNode(NodeGP node){
        this.nodes.add(node);
    }

    public List<NodeGP> getNodes(){
        return nodes;
    }

    public void setNodes(List<NodeGP> nodes){
        this.nodes = nodes;
    }
    //endregion
}
