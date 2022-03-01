package at.htl.controller;

import at.htl.entity.Match;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class MatchRepository implements PanacheRepository<Match> {

}
