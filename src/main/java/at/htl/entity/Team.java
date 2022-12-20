package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "T_Team")
public class Team  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "T_ID")
    Long id;

    @Column(name = "T_Name",length = 30)
    String name;

    @Column(name = "T_Abbr")
    private String abbr; //Abbreviation

    @Column(name = "T_Tournament_ID")
    Long tournamentId;

    //region Constructor
    public Team(String name) {
        this.name = name;
    }

    public Team(String name, String abbr) {
        this(name);
        this.abbr = abbr.toUpperCase();
    }

    public Team(String name, String abbr, long tournamentId) {
        this(name,abbr);
        this.tournamentId = tournamentId;
    }

    public Team() {

    }
    //endregion
    //region Getter & Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    //endregion

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return name;
    }
}
