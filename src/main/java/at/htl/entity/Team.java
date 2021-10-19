package at.htl.entity;

public class Team {

    //region fields
    Long teamID;
    String teamName;
    Competition competition;
    //endregion

    //region Constructor
    public Team(Long teamID, String teamName, Competition competition) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.competition = competition;
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
    //endregion
}
