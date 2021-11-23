package at.htl.controller;

import at.htl.entity.Competition;
import at.htl.entity.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PlayerRepository {

    @Inject
    EntityManager entityManager;

    public void save(Player player){
        entityManager.merge(player);
    }


}
