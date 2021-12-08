package prototype;

import org.junit.jupiter.api.Test;
import prototype.entity.*;
import prototype.filewriter.Filewriter;

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

    @Test
    public void getWinningTeamOfMoreThanOneMatch(){
        final String name1 = "team01";
        final String name2 = "team02";
        final String name3 = "team03";
        final String name4 = "team04";

        Team team01 = new Team(name1);
        Team team02 = new Team(name2);
        Team team03 = new Team(name3);
        Team team04 = new Team(name4);

        Match match01 = new Match();
        Match match02 = new Match();
        Match match03 = new Match();

        match01.setTeam1(team01);
        match01.setTeam2(team02);
        match01.setResultOfMatch(new int[]{3,1});

        match02.setTeam1(team03);
        match02.setTeam2(team04);
        match02.setResultOfMatch(new int[]{1,3});

        match03.setTeam1(match01.getWinningTeam());
        match03.setTeam2(match02.getWinningTeam());
        match03.setResultOfMatch(new int[]{3,4});

        assertThat(match03.getWinningTeam().getName()).isEqualTo("team04");
    }


    @Test
    public void testFileWriter(){
        Filewriter filewriter = new Filewriter();
        filewriter.writeResult("team01", "team02" , new int[]{0,1});
    }
}
