package at.htl.comparator;

import at.htl.entity.TeamGP;

import java.util.Comparator;

public class TeamComparator implements Comparator<TeamGP> {

    @Override
    public int compare(TeamGP team1, TeamGP team2) {
        if(team1.getPoints() > team2.getPoints())
            return -1;
        return 1;
    }
}
