package at.htl.controller;

import at.htl.entity.Competition;
import at.htl.entity.Player;
import at.htl.entity.Team;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDate;

@ApplicationScoped
public class initBean {

    @Inject
    TeamRepository teamRepository;

    void onStart(@Observes StartupEvent startupEvent){

    }

    void repoInsert(){

        //region Competitions
        Competition competition1 = new Competition("BierPong", LocalDate.parse("2021-04-02"));
        Competition competition2 = new Competition("Fussballtunier", LocalDate.parse("2021-01-12"));
        //endregion

        //region Players
        Player steffen = new Player(LocalDate.parse("2005-01-22"), "Steffen", "Schreiber");
        Player edelgard = new Player(LocalDate.parse("2007-01-13"), "Edelgard", "Friedrich");
        Player cordula = new Player(LocalDate.parse("2007-04-10"), "Cordula", "Protz");
        Player nikolas = new Player(LocalDate.parse("2005-05-01"), "Nikolas", "Schmidt");
        Player gisela = new Player(LocalDate.parse("2004-06-04"), "Gisela", "Berger");
        Player hubert = new Player(LocalDate.parse("2007-03-06"), "Hubert", "Teufel");
        Player christiane = new Player(LocalDate.parse("2005-11-27"), "Christiane", "Engel");
        //endregion

        //region Teams
        Team java4Life = new Team("Java4Life", competition1, steffen);
        Team oneShots = new Team("OneShots", competition1, edelgard);
        Team streak = new Team("Streak", competition1, cordula);
        Team fungus = new Team("Fungus", competition1, nikolas);
        Team weak = new Team("Weak", competition2, gisela);
        Team newGuy = new Team("NewGuy", competition2, hubert);
        Team kp = new Team("Kp", competition2, christiane);
        //endregion
    }
}
