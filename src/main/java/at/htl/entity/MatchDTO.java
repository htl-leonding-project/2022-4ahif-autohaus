package at.htl.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MatchDTO {

    private Long id;
    public Team team1;
    public Team team2;
    public int pointsTeam1;
    public int pointsTeam2;

    public boolean finished = false;

    public int phase;

    public MatchDTO(Long id, Team team1, Team team2, int pointsTeam1, int pointsTeam2, boolean finished, int phase) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.pointsTeam1 = pointsTeam1;
        this.pointsTeam2 = pointsTeam2;
        this.finished = finished;
        this.phase = phase;
    }

    public MatchDTO(Match match, Phase phase){
        this.id = match.getId();
        this.team1 = match.getTeam1();
        this.team2 = match.getTeam2();
        this.pointsTeam1 = match.getPointsTeam1();
        this.pointsTeam2 = match.getPointsTeam2();
        this.finished = match.isFinished();
        this.phase = phase.level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPointsTeam1() {
        return pointsTeam1;
    }

    public void setPointsTeam1(int pointsTeam1) {
        this.pointsTeam1 = pointsTeam1;
    }

    public int getPointsTeam2() {
        return pointsTeam2;
    }

    public void setPointsTeam2(int pointsTeam2) {
        this.pointsTeam2 = pointsTeam2;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}
