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
        this.name = name;
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
        this.name = name;
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
}
