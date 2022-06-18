package at.htl.control;

import at.htl.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
<<<<<<< HEAD:src/main/java/at/htl/controller/TeamRepository.java
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
=======
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
>>>>>>> df541ecf21b1ddb3088d598354cdbfa15acdc9de:src/main/java/at/htl/control/TeamRepository.java

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {
    public List<Team> setTeamsForTournament(int amount){
        List<Team> teams = new ArrayList<>();
        for(int i=1; i<amount+1; i++){
            teams.add(this.findById((long)i));
        }
        return teams;
    }
}
