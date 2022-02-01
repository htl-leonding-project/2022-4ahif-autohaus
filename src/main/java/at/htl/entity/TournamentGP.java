package at.htl.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class TournamentGP {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //erst einmal leer lassen
    @Column(length = 20)
    String name;

    List<PhaseGP>phases = new ArrayList<PhaseGP>();

    List<GroupGP> groups = new ArrayList<GroupGP>();

    public TournamentGP(String name) {
        this.name = name;
    }

    public TournamentGP() {
    }

    public List<PhaseGP> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseGP> phases) {
        this.phases = phases;
    }

    public void addPhase(PhaseGP phase){this.phases.add(phase);}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupGP> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupGP> groups) {
        this.groups = groups;
    }

    public void addGroup(GroupGP group){this.groups.add(group);}

    public String getConnections(){
        String connections = "";

        for (PhaseGP phase: phases) {
            for (NodeGP node: phase.getNodes()) {
                for(GroupGP group: groups){
                    if(group.getTeams().contains(node.getCurMatch().getTeam1())){
                        connections += String.format("""
                        %s -- %s
                        """, group.getGroupName(), node.getMatchName());
                    }
                }

            }
        }
        return connections;
    }
}
