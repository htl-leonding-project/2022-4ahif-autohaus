package at.htl.controller;

import at.htl.entity.Match;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class MatchRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    MatchRepository matchRepository;

    @Transactional
    public Match save(Match match)
    {
        return entityManager.merge(match);
    }
}
