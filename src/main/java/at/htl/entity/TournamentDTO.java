package at.htl.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentDTO {

    Long id;
    String name;
    LocalDate startDate;
    Status status;

    public TournamentDTO(Long id, String name, LocalDate startDate, Status status) {
        this.id = id;
        this.name = removeIllegalCharactersFromTournamentName(name);
        this.startDate = startDate;
        this.status = status;
    }

    public TournamentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = removeIllegalCharactersFromTournamentName(name);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String removeIllegalCharactersFromTournamentName(String name) {

        // remove illegal characters
        String newName = name.replaceAll("[:\\\\/*?|<>]", "_");

        // replace double or more spaces with a single one
        newName = newName.replaceAll("_{2,}", "_");

        //remove the _ if at the end
        if (newName.endsWith("_")) {
            newName = newName.substring(0, newName.length() - 1);
        }

        return newName;
    }
}
