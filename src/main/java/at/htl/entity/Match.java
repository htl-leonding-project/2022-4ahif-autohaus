package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lc_match")
public class Match{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    public Team team1;
    @ManyToOne
    public Team team2;
    public int pointsTeam1;
    public int pointsTeam2;

    //resultOfMatch[0] -> GoalsTeam1
    //resultOfMatch[1] -> GoalsTeam2

    public Match() {
    }

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    //region Getter & Setter
    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getPointsTeam1(){return this.pointsTeam1;}

    public void setPointsTeam1(int amount){ this.pointsTeam1 = amount; }

    public int getPointsTeam2(){return this.pointsTeam2;}

    public void setPointsTeam2(int amount){ this.pointsTeam2 = amount; }

    public void increasePointsTeam1(){
        this.pointsTeam1++;
    }

    public void increasePointsTeam2(){
        this.pointsTeam2++;
    }

    public void endMatch(){
        team1.incrementPoints(pointsTeam1);
        team2.incrementPoints(pointsTeam2);
    }
    //endregion


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getWinningTeam(){
        if(pointsTeam1 > pointsTeam2)
            return team1;
        else if (pointsTeam2 > pointsTeam1)
            return team2;

        return null;//Bei Gleichstand noch unklar
    }

    public String getMatchResultString() {
        return getTeam1().getName() +" vs. "+getTeam2().getName()
                +" "+getPointsTeam1()
                +":"+getPointsTeam2();
    }
}
