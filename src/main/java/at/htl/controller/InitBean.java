package at.htl.controller;

import at.htl.entity.Team;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {
    @Inject
    TeamRepository teamRepository;

    @Transactional
    void startup(@Observes StartupEvent event) {
        Team team1 = new Team("Lask Linz","Lask");
        teamRepository.persist(team1);
        Team team2 = new Team("Real Madrid","RM");
        teamRepository.persist(team2);
        Team team3 = new Team("FC Barcelona","FCB");
        teamRepository.persist(team3);
        Team team4 = new Team("Red Bull Salzburg","RBS");
        teamRepository.persist(team4);
        Team team5 = new Team("Rapid Wien","RW");
        teamRepository.persist(team5);
        Team team6 = new Team("Chelsea","C");
        teamRepository.persist(team6);
        Team team7 = new Team("Manchester United","MU");
        teamRepository.persist(team7);
        Team team8 = new Team("Manchester City","MC");
        teamRepository.persist(team8);
        Team team9 = new Team("Atletico Madrid","AM");
        teamRepository.persist(team9);
        Team team10 = new Team("FC Liverpool","FCL");
        teamRepository.persist(team10);
        Team team11 = new Team("AS Rom","ASR");
        teamRepository.persist(team11);
        Team team12 = new Team("FC Arsenal","FCA");
        teamRepository.persist(team12);
        Team team13 = new Team("AC Mailand","ACM");
        teamRepository.persist(team13);
        Team team14 = new Team("Inter Mailand","IM");
        teamRepository.persist(team14);
        Team team15 = new Team("FC Porto","FCP");
        teamRepository.persist(team15);
        Team team16 = new Team("Ajax Amsterdam","AA");
        teamRepository.persist(team16);
    }
}