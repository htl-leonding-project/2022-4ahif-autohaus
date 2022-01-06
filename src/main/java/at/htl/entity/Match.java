package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class Match extends PanacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 20)
    public Team team1;
    @Column(length = 20)
    public Team team2;
    public int[] resultOfMatch = new int[2];
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

    public int[] getResultOfMatch() {
        return resultOfMatch;
    }

    public void setResultOfMatch(int[] resultOfMatch) {
        this.resultOfMatch = resultOfMatch;
    }
    //endregion

    public Team getWinningTeam(){
        if(resultOfMatch[0] > resultOfMatch[1])
            return team1;
        else if (resultOfMatch[1] > resultOfMatch[0])
            return team2;

        return null;//Bei Gleichstand noch unklar
    }

    public String getMatchResultString() {
        return getTeam1().getName() +" vs. "+getTeam2().getName()
                +" "+getResultOfMatch()[0]
                +":"+getResultOfMatch()[1];
    }
}
