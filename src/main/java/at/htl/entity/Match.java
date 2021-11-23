package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Match {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long matchID;
    Team team1;
    Team team2;
    int pointsOfTeam1;
    int pointsOfTeam2;
    Phase phase;
    //endregion

    //region Constructor
    public Match(Long matchID, Team team1, Team team2, int pointsOfTeam1, int pointsOfTeam2, Phase phase) {
        this.matchID = matchID;
        this.team1 = team1;
        this.team2 = team2;
        this.pointsOfTeam1 = pointsOfTeam1;
        this.pointsOfTeam2 = pointsOfTeam2;
        this.phase = phase;
    }

    public Match() {
    }
    //endregion

    //region Getter and Setter
    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }

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

    public int getPointsOfTeam1() {
        return pointsOfTeam1;
    }

    public void setPointsOfTeam1(int pointsOfTeam1) {
        this.pointsOfTeam1 = pointsOfTeam1;
    }

    public int getPointsOfTeam2() {
        return pointsOfTeam2;
    }

    public void setPointsOfTeam2(int pointsOfTeam2) {
        this.pointsOfTeam2 = pointsOfTeam2;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    //endregion
}
