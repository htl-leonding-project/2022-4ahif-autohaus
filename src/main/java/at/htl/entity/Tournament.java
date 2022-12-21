package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @Column(name = "T_Name")
    String name;

    @Column(name = "T_Status")
    Status status;

    @Transient
    List<Phase> phases = new ArrayList<>();

    @JoinColumn(name = "T_FinalNode")
    @OneToOne(cascade = CascadeType.ALL)
    Node finalNode;

    @Column(name="T_StartDate")
    LocalDate startDate;

    public Tournament(String name) {
        this();
        this.name = name;
        this.status = Status.IN_PREPARATION;
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

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phases=" + phases +
                '}';
    }
}
