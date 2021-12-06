package at.htl.entity;

import javax.persistence.*;

@Entity
public class Team {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long teamID;
    String teamName;
    @ManyToOne
    Competition competition;
    @ManyToOne
    Player player;
    //endregion

    //region Constructor
    public Team(String teamName, Competition competition,Player player) {
        this.teamName = teamName;
        this.competition = competition;
        this.player = player;
    }

    public Team() {
    }
    //endregion

    //region Getter and Setter
    public Long getTeamID() {
        return teamID;
    }

    public void setTeamID(Long teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    //endregion
}
