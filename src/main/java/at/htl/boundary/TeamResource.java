package at.htl.boundary;

import at.htl.controller.TeamRepository;
import at.htl.entity.Team;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("Teams")
public class TeamResource {

    @Inject
    TeamRepository teamRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTeams(){
        List<Team> all = teamRepo.listAll();
        return Response.ok().build();
    }

}
