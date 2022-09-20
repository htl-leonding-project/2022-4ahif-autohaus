package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "T_Tournament")
@Entity
public class Tournament  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    private Long id;
    //erst einmal leer lassen
    @Column(name = "T_Name",length = 20)
    String name;
    @Transient
    List<Phase> phases = new ArrayList<>();
    @Column(name = "T_GPGroups")
    @OneToMany(cascade = CascadeType.ALL)
    List<GroupGP> GPgroups = new ArrayList<>();

    @JoinColumn(name = "T_FinalNode")
    @OneToOne(cascade = CascadeType.ALL)
    Node finalNode;

    @Column(name="T_StartDate")
    LocalDate startDate;

    public Tournament(String name) {
        this();
        this.name = name;
    }

    public Tournament() {
        this.startDate = LocalDate.now();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public void addPhase(Phase phase) {this.phases.add(phase);}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupGP> getGroups() {
        return GPgroups;
    }

    public void setGroups(List<GroupGP> groups) {
        this.GPgroups = groups;
    }

    public void addGroup(GroupGP group){this.GPgroups.add(group);}

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public String GPGetConnections(){
        String connections = "";

        for (Phase phase: phases) {
            for (Node node: phase.getGPNodes()) {
                for(GroupGP group: GPgroups){
                    if(group.getTeams().contains(node.getCurMatch().getTeam1())){
                        connections += String.format("""
                        %s -- %s
                        """, group.getGroupName(), node.getMatchNameGP());
                    }
                }

            }
        }
        return connections;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phases=" + phases +
                ", GPgroups=" + GPgroups +
                '}';
    }
}
