package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Team  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 20)
    String name;
    int points;

    private String abbr; //Abbreviation

    //region Constructor
    public Team() {
    }

    public Team(String name) {
        this.name = name;
        this.points = 0;
    }

    public Team(String name, String abbr) {
        this.name = name;
        this.abbr = abbr;
        this.points = 0;
    }
    //endregion
    //region Getter & Setter
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

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
    public String getNameAndAbbr() {
        return this.getName() + "=>"+ this.getAbbr();
    }

    public void incrementPoints(int amount){this.points+=amount;}
}
