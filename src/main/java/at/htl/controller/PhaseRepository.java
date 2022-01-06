package at.htl.controller;

import at.htl.entity.Phase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PhaseRepository {

    @Inject
    EntityManager entityManager;

    public Phase save(Phase phase){
        return this.entityManager.merge(phase);
    }
}
