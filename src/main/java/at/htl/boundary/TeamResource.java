package at.htl.boundary;

import at.htl.controller.TeamRepository;
import at.htl.entity.Team;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teams")
public class TeamResource {

    @Inject
    TeamRepository teamRepo;

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTeams(){
        List<Team> all = teamRepo.listAll();
        return Response.ok(all).build();
    }
*/

    @Path("add")
    @POST
    @Transactional
    public void addTeam(@FormParam("name") String name, @FormParam("abbr") String abbr)
    {
        Team newTeam = new Team(name,abbr);
        teamRepo.persist(newTeam);
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance addTeam();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return Templates.addTeam();
    }
}
