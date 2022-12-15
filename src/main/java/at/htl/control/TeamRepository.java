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

    public List<Team> getAllSorted(){
        return this.findAll().stream().sorted(Comparator.comparing(Team::getAbbr)).toList();
    }

    public Team getTeamByAbbr(String abbr){
        return this.find("abbr=?1", abbr).firstResult();
    }

    public List<Team> getByTournamentId(Long id){
        return this.find("tournamentId=?1", id).stream().toList();
    }
}
