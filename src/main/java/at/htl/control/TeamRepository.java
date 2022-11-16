package at.htl.control;

import at.htl.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    @Inject
    Logger log;

    public List<Team> setTeamsForTournament(int amount){
        List<Team> teams = new ArrayList<>();
        for(int i=1; i<amount+1; i++){
            teams.add(this.findById((long)i));
        }
        return teams;
    }

    public List<Team> getTeamsByIds(List<Long> ids){
        return getEntityManager()
                .createQuery("SELECT t FROM Team t WHERE t.id IN :ids", Team.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public void persist(Team team) {
        log.info(team.getName() +", " + team.getAbbr());
        if(this.find("name = ?1 and abbr = ?2", team.getName(), team.getAbbr()).count() > 0) {
            log.error("Team with same attributes already saved");
        }
        else {
            PanacheRepository.super.persist(team);
        }
    }

    public List<Long> getUnusedTeamIds() {
        return this.getEntityManager()
                .createQuery("select id from Team where id not in (select team.id from GroupGP ggp join ggp.teams team)",
                        Long.class).getResultList();
    }

    public List<Team> getAllSorted(){
        return this.findAll().stream().sorted(Comparator.comparing(Team::getAbbr)).toList();
    }

    public Team getTeamByAbbr(String abbr){
        return this.find("abbr=?1", abbr).firstResult();
    }
}
