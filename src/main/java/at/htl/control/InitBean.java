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

    //@Inject
    //MatchRepository matchRepository;

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
/*
        teams.add(new Team("AC Mailand",""));
        teams.add(new Team("Ajax Amsterdam",""));
        teams.add(new Team("Atalanta Bergamo",""));
        teams.add(new Team("Atletico Madrid",""));
        teams.add(new Team("Benfica Lissabon",""));
        teams.add(new Team("Besiktas Istanbul",""));
        teams.add(new Team("Borussia Dortmund",""));
        teams.add(new Team("BSC Young Boys",""));
        teams.add(new Team("Chelsea FC",""));
        teams.add(new Team("Dynamo Kiew",""));
        teams.add(new Team("FC Barcelona",""));
        teams.add(new Team("FC Bayern München",""));
        teams.add(new Team("FC Brügge",""));
        teams.add(new Team("FC Porto",""));
        teams.add(new Team("FC Villareal",""));
        teams.add(new Team("Inter Mailand",""));
        teams.add(new Team("Juventus Turin",""));
        teams.add(new Team("Liverpool FC",""));
        teams.add(new Team("Malmö FF",""));
        teams.add(new Team("Manchester City",""));
        teams.add(new Team("Manchester United FC",""));
        teams.add(new Team("OSC Lille",""));
        teams.add(new Team("Paris St. Germain",""));
        teams.add(new Team("RB Leipzig",""));
        teams.add(new Team("RB Salzburg",""));
        teams.add(new Team("Real Madrid",""));
        teams.add(new Team("Schachtar Donezk",""));
        teams.add(new Team("Sevilla FC",""));
        teams.add(new Team("Sheriff Tiraspol",""));
        teams.add(new Team("Sporting Lissabon",""));
        teams.add(new Team("VfL Wolfsburg",""));
        teams.add(new Team("Zenit St. Petersburg",""));
*/
        teamRepository.persist(teams);

        //matchRepository.persist(new Match(new Team("A","a"),new Team("B","b")));
    }
}