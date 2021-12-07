package prototype;

import org.junit.jupiter.api.Test;
import prototype.entity.Match;
import prototype.entity.Team;

import static org.assertj.core.api.Assertions.*;


public class PrototypeTest {

    @Test
    public void getWinningTeamTest(){
        final String name1 = "team01";
        final String name2 = "team02";

        Team team01 = new Team(name1);
        Team team02 = new Team(name2);
        Match match = new Match();
        match.setTeam1(team01);
        match.setTeam2(team02);
        match.setResultOfMatch(new int[]{0,1});

        assertThat(match.getWinningTeam().getName()).isEqualTo(name2);
    }

}
