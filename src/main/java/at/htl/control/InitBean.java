package at.htl.control;

import at.htl.entity.Match;
import at.htl.entity.Team;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {
    @Inject
    TeamRepository teamRepository;

    @Inject
    MatchRepository matchRepository;

    @Transactional
    void startup(@Observes StartupEvent event) {

        List<Team> teams = new ArrayList<>();

        teams.add(new Team("Lask Linz","Lask"));
        teams.add(new Team("Real Madrid","RM"));
        teams.add(new Team("FC Barcelona","FCB"));
        teams.add(new Team("Red Bull Salzburg","RBS"));
        teams.add(new Team("Rapid Wien","RW"));
        teams.add(new Team("Chelsea","C"));
        teams.add(new Team("Manchester United","MU"));
        teams.add(new Team("Manchester City","MC"));
        teams.add(new Team("Atletico Madrid","AM"));
        teams.add(new Team("FC Liverpool","FCL"));
        teams.add(new Team("AS Rom","ASR"));
        teams.add(new Team("FC Arsenal","FCA"));
        teams.add(new Team("AC Mailand","ACM"));
        teams.add(new Team("Inter Mailand","IM"));
        teams.add(new Team("FC Porto","FCP"));
        teams.add(new Team("Ajax Amsterdam","AA"));

        teamRepository.persist(teams);

        matchRepository.persist(new Match(new Team("A","a"),new Team("B","b")));
    }
}