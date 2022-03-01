package at.htl.comparator;

import at.htl.entity.Team;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {

    @Override
    public int compare(Team team1, Team team2) {
        if(team1.getPoints() > team2.getPoints())
            return -1;
        return 1;
    }
}
