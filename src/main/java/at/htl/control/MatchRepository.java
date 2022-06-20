package at.htl.control;

import at.htl.entity.Match;
import at.htl.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class MatchRepository implements PanacheRepository<Match> {

    public List<Match> matchTeams(List<Team>teams){
        List<Match> matches = new LinkedList<>();

        for(int i = 0; i < teams.size(); i+=2){
            matches.add(new Match(teams.get(i), teams.get(i+1)));
        }

        return matches;
    }
}
