package at.htl.entity;

import java.time.LocalDate;

public class Competition {

    //region fields
    Long competitionID;
    String nameOfTheCompetition;
    LocalDate dateOfTheCompetition;
    //endregion

    //region Constructor
    public Competition(Long competitionID, String nameOfTheCompetition, LocalDate dateOfTheCompetition) {
        this.competitionID = competitionID;
        this.nameOfTheCompetition = nameOfTheCompetition;
        this.dateOfTheCompetition = dateOfTheCompetition;
    }

    public Competition() {
    }
    //endregion

    //region Getter and Setter
    public Long getCompetitionID() {
        return competitionID;
    }

    public void setCompetitionID(Long competitionID) {
        this.competitionID = competitionID;
    }

    public String getNameOfTheCompetition() {
        return nameOfTheCompetition;
    }

    public void setNameOfTheCompetition(String nameOfTheCompetition) {
        this.nameOfTheCompetition = nameOfTheCompetition;
    }

    public LocalDate getDateOfTheCompetition() {
        return dateOfTheCompetition;
    }

    public void setDateOfTheCompetition(LocalDate dateOfTheCompetition) {
        this.dateOfTheCompetition = dateOfTheCompetition;
    }
    //endregion
}
