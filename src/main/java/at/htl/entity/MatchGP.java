package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

@Entity
public class MatchGP  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TeamGP team1;
    @ManyToOne
    private TeamGP team2;
    private int team1Points = 0;
    private int team2Points = 0;

    public TeamGP getWinningTeam(){
        if(team1.getPoints() > team2.getPoints())
            return team1;
        else if (team2.getPoints() > team1.getPoints())
            return team2;

        // Gleichstand gibt es nicht da BierPong immer zu Ende gespielt wird,
        // bei Fußball würden Elfmeter geschossen werden.
        return null;
    }

    //region Constructor

    public MatchGP(TeamGP team1, TeamGP team2) {
        this.team1 = team1;
        this.team2 = team2;
    }
    public MatchGP() {
    }

    //endregion
    //region Getter and Setter

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public TeamGP getTeam1() {
        return team1;
    }

    public void setTeam1(TeamGP team1) {
        this.team1 = team1;
    }

    public TeamGP getTeam2() {
        return team2;
    }

    public void setTeam2(TeamGP team2) {
        this.team2 = team2;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public void setTeam1Points(int team1Points) {
        this.team1Points = team1Points;
    }

    public void increaseTeam1Points(){
        this.team1Points++;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public void setTeam2Points(int team2Points) {
        this.team2Points = team2Points;
    }

    public void increaseTeam2Points(){
        this.team2Points++;
    }

    public void endMatch(){
        team1.incrementPoints(team1Points);
        team2.incrementPoints(team2Points);
    }

    //endregion
}
