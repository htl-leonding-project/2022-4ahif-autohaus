package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class Tournament  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //erst einmal leer lassen
    @Column(length = 20)
    String name;

    List<Phase> phases = new ArrayList<>();
    List<Group> GPgroups = new ArrayList<>();

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

    public void addPhase(Phase phase) {this.phases.add(phase);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Group> getGroups() {
        return GPgroups;
    }

    public void setGroups(List<Group> groups) {
        this.GPgroups = groups;
    }

    public void addGroup(Group group){this.GPgroups.add(group);}

    public String GPGetConnections(){
        String connections = "";

        for (Phase phase: phases) {
            for (Node node: phase.getGPNodes()) {
                for(Group group: GPgroups){
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
}
