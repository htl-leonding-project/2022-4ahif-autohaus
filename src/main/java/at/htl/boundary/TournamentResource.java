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
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
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
    private int phaseForCurrentTournament = 5;
    @Inject
    TeamRepository teamRepository;
    @Inject
    TournamentRepository tournamentRepository;

    @Inject
    Logger log;

    @Inject
    NodeRepository nodeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTournaments(){
        return Response.ok(
                tournamentRepository.findAll().list()).build();
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
        return Response.ok(tournamentRepository.getMatches(tournamentRepository.findByName(name))).build();
    }

    @POST
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @PathParam("name") String name, List<Team> teams
    ) {
        tournamentRepository.setUpTournament(name, teams);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/finished/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response finished(
            @PathParam("name") String name
    ) {
        if(tournamentRepository.findByName(name).getFinalNode().getCurMatch()!=null)
            if(tournamentRepository.findByName(name).getFinalNode().getCurMatch().getWinningTeam()!= null)
                return Response.ok(true).build();
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
        List<Node> nodes;
        String tournamentName = "";
        if(ids.size() == 4 || ids.size() == 8 || ids.size() == 16){
            if(name.equals("")){
                tournamentName = "Turnier_am_"+LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            }
            else{
                tournamentName = name.replace(" ", "_");

                if(tournamentRepository.findByName(tournamentName)!= null){
                    return Response
                            .status(301)
                            .location(URI.create("tournaments/createTournament"))
                            .build();
                }

            }
            nodes = tournamentRepository.setUpTournament(tournamentName, teamRepository.getTeamsByIds(ids));
            phaseForCurrentTournament = nodes.get(0).getPhase().getLevel();
            log.info(phaseForCurrentTournament);

            return Response
                    .status(301)
                    .location(URI.create("tournaments/matchList/"+tournamentRepository.findByName(tournamentName).getId()))
                    .build();
        }
        return Response
                .status(301)
                .location(URI.create("tournaments/createTournament"))
                .build();
    }

}
