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
import at.htl.entity.*;
import at.htl.filewriter.Filewriter;
import org.jboss.logging.Logger;

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

    private final String PATH="/c.handel/api/tournaments";
    @Inject
    TeamRepository teamRepository;
    @Inject
    TournamentRepository tournamentRepository;

    @Inject
    Logger log;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTournaments(){
        List<Tournament> tournaments = tournamentRepository.getAllSorted();
        List<TournamentDTO> dtoTournaments = new LinkedList<>();

        for (Tournament t: tournaments) {
            dtoTournaments.add(new TournamentDTO(t.getId(), t.getName(), t.getStartDate(), t.getStatus()));
        }

        return Response.ok(dtoTournaments).build();
    }

    @GET
    @Path(value = "/amount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response amount(){
        return Response.ok(tournamentRepository.findAll().count()).build();
    }

    @GET
    @Path("/matches/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatches(@PathParam("name") String name){
        return Response.ok(tournamentRepository.getMatchesDto(tournamentRepository.findByName(name.replace(" ", "_")))).build();
    }

    @POST
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @PathParam("name") String name, List<Team> teams
    ) {
        tournamentRepository.setUpTournament(name.replace(" ", "_"), teams);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/finished/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response finished(
            @PathParam("name") String nameUnchanged
    ) {
        String name = nameUnchanged.replace(" ", "_");
        if(tournamentRepository.findByName(name).getFinalNode().getCurMatch()!=null)
            if(tournamentRepository.findByName(name).getFinalNode().getCurMatch().getWinningTeam()!= null) {
                tournamentRepository.findByName(name).setStatus(Status.FINISHED);
                return Response.ok(true).build();
            }
        return Response.ok(false).build();
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

    @GET
    @Path("/exists/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tournamentNameExists(@PathParam("name") String name){
        if(tournamentRepository.findByName(name.replace(" ","_")) != null)
            return Response.ok(true).build();
        return Response.ok(false).build();
    }

    @GET
    @Path("/generate/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateDiagram(@PathParam("name") String nameUnchanged){
        Filewriter filewriter = new Filewriter();
        String name = nameUnchanged.replace(" ", "_");

        filewriter.writeFinalResult(
                tournamentRepository.findByName(name).getFinalNode(),
                tournamentRepository.findByName(name)
        );
        return Response.ok().build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/listTournaments")
    public Response moveToDiagram(@FormParam("name") String name){
        return Response.status(301)
                .location(URI.create(PATH+"/showEndResult/"+name.replace(" ", "_")))
                .build();
    }

    @Path("/createTournament")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response createTournament(
            @Context UriInfo uriInfo
            , @FormParam("id") List<Team> teams
            , @FormParam("tournamentName") String name
    ) {
        List<Node> nodes;
        String tournamentName = "";
        int phaseForCurrentTournament;
        if(teams.size() == 4 || teams.size() == 8 || teams.size() == 16){
            if(name.equals("")){
                tournamentName = "Turnier_am_"+LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            }
            else{
                tournamentName = name.replace(" ", "_");

                if(tournamentRepository.findByName(tournamentName)!= null){
                    return Response
                            .status(301)
                            .location(URI.create(PATH+"/createTournament"))
                            .build();
                }

            }

            teamRepository.persist(teams);

            nodes = tournamentRepository.setUpTournament(tournamentName, teams);
            phaseForCurrentTournament = nodes.get(0).getPhase().getLevel();
            log.info(phaseForCurrentTournament);

            return Response
                    .status(301)
                    .location(URI.create(PATH+"/matchList/"+tournamentRepository.findByName(tournamentName).getId()))
                    .build();
        }
        return Response
                .status(301)
                .location(URI.create(PATH+"/createTournament"))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteTournament(
            @PathParam("id") Long id
    ){
        tournamentRepository.deleteTournamentById(id);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("teams/{name}")
    public Response getTeamsByTournament(@PathParam("name") String name){
        long id = tournamentRepository.findByName(name.replace(" ", "_")).getId();
        return Response.ok(teamRepository.find("tournamentId = ?1",id).stream().toList()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("set-matches/{name}")
    public Response setUpMatches(@PathParam("name") String name, @FormParam("teams") List<Team> teams){
        tournamentRepository.generateMatches(name, teams);
        return Response.ok().build();
    }
}
