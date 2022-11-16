package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Table(name = "T_Team")
public class Team  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "T_ID")
    Long id;

    @Column(name = "T_Name",length = 30)
    String name;

    @Column(name = "T_Abbr")
    private String abbr; //Abbreviation

    @Column(name = "T_Overall_Wins")
    private int winAmount;

    //region Constructor
    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(String name, String abbr) {
        this.name = name;
        this.abbr = abbr.toUpperCase();
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
    public String getNameAndAbbr() {
        return this.getName() + "=>"+ this.getAbbr();
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(int amount) {
        this.winAmount = amount;
    }

    public void incrementWinAmount(){
        this.winAmount++;
    }

    @Override
    public String toString() {
        return name;
    }
}
