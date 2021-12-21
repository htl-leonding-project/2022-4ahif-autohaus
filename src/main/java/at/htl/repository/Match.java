package at.htl.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class Match {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Match save(Match match){
        return entityManager.merge(match);
    }

    public List<Match> findAll(){
        return entityManager
                .createNamedQuery("Match.findAll",Match.class)
                .getResultList();
    }

}
