package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TeamGP  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int points;

    private String abbr; //Abbreviation

    //region Constructor

    public TeamGP(String name, String abbr, int points) {
        this.name = name;
        this.abbr = abbr;
        this.points = points;
    }
    public TeamGP() {
    }

    //endregion
    //region Getter and Setter

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
