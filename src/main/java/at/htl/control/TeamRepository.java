package at.htl.control;

import at.htl.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {
    public List<Team> setTeamsForTournament(int amount){
        List<Team> teams = new ArrayList<>();
        for(int i=1; i<amount+1; i++){
            teams.add(this.findById((long)i));
        }
        return teams;
    }

    public List<Team> getTeamsByIds(List<Integer> ids){
        List<Team> teams = new LinkedList<>();
        for (int id:ids) {
            teams.add(this.findById((long)id));
        }
        return teams;
    }

    @Override
    public void persist(Team team) {
        if(this.find("name = ?1 and abbr = ?2", team.getName(), team.getAbbr()).count() > 0) {
            throw new PersistenceException();
        }
        PanacheRepository.super.persist(team);
    }

    public List<Long> getUnusedTeamIds() {
        return this.getEntityManager()
                .createQuery("select id from Team where id not in (select team.id from GroupGP ggp join ggp.teams team)",
                        Long.class).getResultList();
    }
}
