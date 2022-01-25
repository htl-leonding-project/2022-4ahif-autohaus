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
}
