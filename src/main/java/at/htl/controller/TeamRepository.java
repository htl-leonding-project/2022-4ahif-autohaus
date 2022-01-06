package at.htl.controller;

import at.htl.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class TeamRepository {

    @Inject
    EntityManager entityManager;

    public Team save(Team team){
        return this.entityManager.merge(team);
    }
}
