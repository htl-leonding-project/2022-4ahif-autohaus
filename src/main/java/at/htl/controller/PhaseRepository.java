package at.htl.controller;

import at.htl.entity.Phase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PhaseRepository implements PanacheRepository<Phase> {

}
