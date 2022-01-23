package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PhaseGP extends PanacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phasenName;
    @OneToMany
    private List<MatchGP> matches;

    //region Constructor
    public PhaseGP(String phasenName, List<MatchGP> matches) {
        this.phasenName = phasenName;
        this.matches = matches;
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

    public List<MatchGP> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchGP> matches) {
        this.matches = matches;
    }
    //endregion
}
