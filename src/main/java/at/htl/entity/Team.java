package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Team extends PanacheEntity {


    @Column(length = 20)
    String name;
    int points;

    //region Constructor
    public Team() {
    }

    public Team(String name) {
        this.name = name;
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
    //endregion

}
