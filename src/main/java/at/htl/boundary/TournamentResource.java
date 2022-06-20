package at.htl.boundary;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import at.htl.control.TeamRepository;
import at.htl.control.TournamentRepository;
import at.htl.entity.GroupGP;
import at.htl.entity.Team;
import at.htl.entity.Tournament;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Path("/tournaments")
public class TournamentResource {


    @Inject
    TeamRepository teamRepository;

    @Inject
    TournamentRepository tournamentRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance TeamsSelect();
    }

    @GET
    @Path("/TeamsSelect")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getTeams(){

        return TournamentResource.Templates.TeamsSelect();
    }

    public Tournament randomGroups(List<Team> teams, int teamsToCreate){

        Tournament tournament = tournamentRepository.findByName("BierPong");
        if(tournament == null) {
            tournament = new Tournament("BierPong");
        }

        int nrOfTeams = teams.size();

        if(teamsToCreate > nrOfTeams){
            return null;
        }

        Random random = new Random();
        List<Integer> randomNumbers = new LinkedList<>();
        // zufälliges befüllen der Gruppen
        int i = 0;
        while(i < teamsToCreate)
        {
            int randomNumber = random.nextInt(nrOfTeams);
            boolean isDuplicate = false;
            for(int j = 0; j < i; j++)
            {
                if (randomNumbers.contains(randomNumber)) {
                    isDuplicate = true;
                    break;
                }
            }
            if(!isDuplicate)
            {
                randomNumbers.add(randomNumber);
                i++;
            }
        }

        List<Team> listofGroup1 = new ArrayList<>();
        for (int j = 0; j < teamsToCreate; j++) {
            Team team = teams.get(randomNumbers.get(j));
            listofGroup1.add(team);
        }
        /*Team team2 = teams.get(randomNumbers.get(1));
        Team team3 = teams.get(randomNumbers.get(2));
        Team team4 = teams.get(randomNumbers.get(3))
        listofGroup1.add(team2);
        listofGroup1.add(team3);
        listofGroup1.add(team4);*/

        GroupGP group1 = new GroupGP("Gruppe A", listofGroup1);

        tournament.addGroup(group1);
        tournamentRepository.persist(tournament);
        return tournament;
    }

    @GET
    @Transactional
    @Path("create/{nrOfTeams}")
    public Response createGroup(@PathParam("nrOfTeams") int nrOfTeams){
        List<Team> teams = teamRepository.listAll();
        Tournament tournament = randomGroups(teams, nrOfTeams);
        List<GroupGP> groups = new ArrayList<>(tournament.getGroups());
        System.out.println("tournament: ");
        return Response
                .temporaryRedirect(URI.create("/groups"))
                .status(301)
                .build();
    }
}
