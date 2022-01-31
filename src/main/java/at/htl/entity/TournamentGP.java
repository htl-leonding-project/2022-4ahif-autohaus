package at.htl.entity;

import org.hibernate.mapping.Collection;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TournamentGP {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //erst einmal leer lassen
    @Column(length = 20)
    String name;

    List<PhaseGP>phases = new ArrayList<PhaseGP>();

    List<GruppeGP> groups = new ArrayList<GruppeGP>();

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

    public List<GruppeGP> getGroups() {
        return groups;
    }

    public void setGroups(List<GruppeGP> groups) {
        this.groups = groups;
    }

    public void addGroup(GruppeGP group){this.groups.add(group);}

    public String getConnections(){
        String connections = "";

        for (PhaseGP phase: phases) {
            for (NodeGP node: phase.getNodes()) {
                for(GruppeGP group: groups){
                    if(group.getTeams().contains(node.getCurMatch().getTeam1())){
                        connections += String.format("""
                        %s -- %s
                        """, group.getGruppenName(), node.getMatchName());
                    }
                }

            }
        }
        return connections;
    }
}
