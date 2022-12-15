package at.htl.control;

import at.htl.entity.Match;
import at.htl.entity.Team;
import at.htl.entity.Tournament;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.tools.JavaFileManager;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class MatchRepository implements PanacheRepository<Match> {

    @Inject
    TeamRepository teamRepository;

    public List<Match> matchTeams(List<Team>teams, Tournament tournament){
        List<Match> matches = new LinkedList<>();

        for(int i = 0; i < teams.size(); i+=2){
            matches.add(new Match(
                    teamRepository.find(
                            "tournamentId=?1 and abbr=?2", -1L, teams.get(i).getAbbr()
                    ).firstResult(),
                    teamRepository.find("tournamentId=?1 and abbr=?2", -1L, teams.get(i+1).getAbbr()
                    ).firstResult()));
        }

        return matches;
    }

    @Override
    public void persist(Match match) {
        PanacheRepository.super.persist(match);
    }
}
