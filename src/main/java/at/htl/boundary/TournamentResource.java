package at.htl.boundary;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;

import at.htl.control.NodeRepository;
import at.htl.control.TeamRepository;
import at.htl.control.TournamentRepository;
import at.htl.entity.GroupGP;
import at.htl.entity.Node;
import at.htl.entity.Team;
import at.htl.entity.Tournament;
import at.htl.filewriter.Filewriter;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import java.io.File;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Path("/tournaments")
public class TournamentResource {

    private final String IMAGE_LOCATION="asciidocs/images/generated-diagrams/";
    @Inject
    TeamRepository teamRepository;
    @Inject
    TournamentRepository tournamentRepository;

    @Inject
    NodeRepository nodeRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance TeamsSelect();
        public static native TemplateInstance tournamentSelection();
        public static native TemplateInstance showEndResult(String name);
        public static native TemplateInstance createTournament(List<Team> teams);
        public static native TemplateInstance listTournaments(List<Tournament> tournaments);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/listTournaments")
    public TemplateInstance getAllTournaments(){
        return TournamentResource.Templates.listTournaments(
                tournamentRepository.findAll().list()
        );
    }

    @GET
    @Path("/TeamsSelect")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getTeams(){

        return TournamentResource.Templates.TeamsSelect();
    }

    @GET
    @Path("/tournamentSelection")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance selectTeam() {
        return TournamentResource.Templates.tournamentSelection();
    }

    @GET
    @Path("/showEndResult/{name}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showEndResult(@PathParam("name") String name){
        return TournamentResource.Templates.showEndResult(name);
    }

    @GET
    @Path("/createTournament")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance createTournament(){
        return TournamentResource.Templates.createTournament(teamRepository.getAllSorted());
    }

    public String convertToLetters(int n) {
        if(n == 0) {
            return "A";
        }
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            int remainder = n % 26;
            char letter = (char) (remainder + 'A');
            result.append(letter);
            n = n / 26;
        }
        return result.toString();
    }

    public Tournament randomGroups(int teamsInGroup){
        Tournament tournament = tournamentRepository.findByName("BierPong");
        if(tournament == null) {
            tournament = new Tournament("BierPong");
        }

        Random random = new Random();

        List<Long> unusedTeams = teamRepository.getUnusedTeamIds();
        if(teamsInGroup > unusedTeams.size()) {
            return null;
        }
        List<Team> listOfGroup = new ArrayList<>();
        for (int i = 0; i < teamsInGroup; i++) {
            int randomNumber = random.nextInt(unusedTeams.size());
            Long teamId = unusedTeams.remove(randomNumber);
            Team team = teamRepository.findById(teamId);
            listOfGroup.add(team);
        }

        int count = tournament.getGroups().size();

        GroupGP group = new GroupGP("Gruppe " + convertToLetters(count), listOfGroup);

        tournament.addGroup(group);
        tournamentRepository.persist(tournament);

        List<Node> nodes = tournamentRepository.setUpTournament(tournament.getName(), group.getTeams());

        Node finalNode = nodes.get(nodes.size()-1);
        Filewriter newFile = new Filewriter();
        newFile.writeFinalResult(finalNode, tournament);

        return tournament;
    }

    @POST
    @Transactional
    @Path("create/{nrOfTeams}")
    public Response createGroup(@PathParam("nrOfTeams") int nrOfTeams){
        if(nrOfTeams %4!= 0){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        for (int i = 0; i < nrOfTeams/4; i++) {
            Tournament tournament = randomGroups(4);
            //TODO: check if tournament is null when null there are not enough teams to create a group

        }
        //List<GroupGP> groups = new ArrayList<>(tournament.getGroups());
        return Response
                .temporaryRedirect(URI.create("/groups"))
                .status(301)
                .build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/listTournaments")
    public Response moveToDiagram(@FormParam("name") String name){
        return Response.status(301)
                .location(URI.create("tournaments/showEndResult/"+name))
                .build();
    }

    @Path("/createTournament")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response createTournament(
            @Context UriInfo uriInfo
            , @FormParam("id") List<Long> ids
            , @FormParam("tournamentName") String name
    ) {
        List<Node> nodes = new ArrayList<>();
        String tournamentName;
        if(ids.size() == 4 || ids.size() == 8 || ids.size() == 16){
            if(name.equals("")){
                tournamentName = "Turnier_am_"+LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            }
            else{
                tournamentName = name;
            }
            nodes = tournamentRepository.setUpTournament(tournamentName, teamRepository.getTeamsByIds(ids));
        }

        return Response.status(301).location(URI.create("/")).build();
    }

    @Path("/tournamentSelection")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response show(
            @Context UriInfo uriInfo
            , @FormParam("name") String name
    ) {
        File image = new File(IMAGE_LOCATION+name+".png");

        if (name.equals("") || !image.exists()) {

            TournamentResource.Templates.tournamentSelection();
            return Response.status(301)
                    .location(URI.create("tournaments/tournamentSelection"))
                    .build();
        }
        else {
            return Response.status(301)
                    .location(URI.create("tournaments/showEndResult/"+name))
                    .build();
        }
    }
}
