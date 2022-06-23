package at.htl.boundary;

import at.htl.control.GroupRepository;
import at.htl.control.TeamRepository;
import at.htl.control.TournamentRepository;
import at.htl.entity.GroupGP;
import at.htl.entity.Team;
import at.htl.entity.Tournament;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Path("/groups")
public class GroupResource {

    @Inject
    TeamRepository teamRepository;

    @Inject
    GroupRepository groupRepository;
    @Inject
    TournamentRepository tournamentRepository;

    Tournament tournament = new Tournament("BierPong");
    Random random = new Random();
    List<Team> teams;
    int nrOfTeams;
    List<Integer> randomNumbers = null;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance groups(List<GroupGP> groups, Tournament tournament);
    }

    public void randomGroups(){

        // zufälliges befüllen der Gruppen
        for(int i = 0; i < 8; i++)
        {
            randomNumbers = new LinkedList<Integer>();
            while (randomNumbers.size() < 8) {
                int randomNumber = random.nextInt(nrOfTeams);
                if (!randomNumbers.contains(randomNumber)) {
                    randomNumbers.add(randomNumber);
                }
            }
        }

        Team team1 = teams.get(randomNumbers.get(0));
        Team team2 = teams.get(randomNumbers.get(1));
        Team team3 = teams.get(randomNumbers.get(2));
        Team team4 = teams.get(randomNumbers.get(3));

        List<Team> listofGroup1 = new ArrayList<>();
        listofGroup1.add(team1);
        listofGroup1.add(team2);
        listofGroup1.add(team3);
        listofGroup1.add(team4);

        GroupGP group1 = new GroupGP("Gruppe A", listofGroup1);

        tournament.addGroup(group1);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getAllGroups(){
        /*teams = teamRepository.listAll();
        nrOfTeams=teamRepository.listAll().size();
        randomGroups();
        List<GroupGP> groups = new ArrayList<>();
        groups.addAll(tournament.getGroups());*/

        Tournament tournament = tournamentRepository.findByName("BierPong");
        if(tournament == null) {
            return Response
                .ok(GroupResource.Templates.groups(new LinkedList<>(), new Tournament("No Tournament found")))
                .build();
        }
        List<GroupGP> groups = new ArrayList<>(tournament.getGroups());
        return Response
                .ok(GroupResource.Templates.groups(groups,tournament))
                .build();
    }

    //post method that creates a group when pushing a button
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response createGroup(String groupName){
        GroupGP group = new GroupGP(groupName,teams);
        tournament.addGroup(group);
        groupRepository.persist(group);
        return Response
                .temporaryRedirect(URI.create("/groups"))
                .status(301)
                .build();

    }

}
