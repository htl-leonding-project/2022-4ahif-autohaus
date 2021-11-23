package at.htl.controller;

import at.htl.entity.Competition;
import at.htl.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CompetitionRepository {

    @Inject
    EntityManager entityManager;

    public void save(Competition competition){
        entityManager.merge(competition);
    }


}
