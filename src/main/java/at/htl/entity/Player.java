package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Player {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long playerID;
    LocalDate dob;
    String firstName;
    String lastName;
    Team teamID;
    //endregion

    //region Constructor
    public Player(Long spielerID, LocalDate dob, String firstName, String lastName, Team teamID) {
        this.playerID = spielerID;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamID = teamID;
    }

    public Player() {
    }
    //endregion

    //region Getter and Setter
    public Long getSpielerID() {
        return playerID;
    }

    public void setSpielerID(Long spielerID) {
        this.playerID = spielerID;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Team getTeamID() {
        return teamID;
    }

    public void setTeamID(Team teamID) {
        this.teamID = teamID;
    }
    //endregion
}