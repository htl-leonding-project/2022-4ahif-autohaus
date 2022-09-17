package at.htl.boundary;

import at.htl.control.NodeRepository;
import at.htl.control.TeamRepository;
import at.htl.control.TournamentRepository;
import at.htl.entity.Team;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Path("teams")
public class TeamResource {

    @Inject
    TeamRepository teamRepo;

    @Inject
    Logger log;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTeams(){
        return Response.ok(teamRepo.getAllSorted()).build();
    }

    @GET
    @Path(value = "/amount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAmount(){
        return Response.ok(teamRepo.getAllSorted().size()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @Context UriInfo uriInfo, Team newTeam
    ) {
        teamRepo.persist(new Team(newTeam.getName(), newTeam.getAbbr()));
        return Response.status(Response.Status.OK).build();
    }

}
