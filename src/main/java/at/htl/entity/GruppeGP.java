package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class GruppeGP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gruppenName;
    @OneToMany
    private List<TeamGP> teams;

    //region Constructor

    public GruppeGP(String gruppenName, List<TeamGP> teams) {
        this.gruppenName = gruppenName;
        this.teams = teams;
    }

    public GruppeGP() {
    }

    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGruppenName() {
        return gruppenName;
    }

    public void setGruppenName(String gruppenName) {
        this.gruppenName = gruppenName;
    }

    public List<TeamGP> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamGP> teams) {
        this.teams = teams;
    }

    public String getGroupMap() {

        String returnString = String.format("""
                map %s {
                %s
                }""", this.getGruppenName(),
                this.getTeams().stream().map(t -> t.getNameAndAbbr()).collect(Collectors.joining("\n")));

        return returnString;

    }
    //endregion
}