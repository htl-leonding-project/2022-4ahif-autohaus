package at.htl.boundary;

import at.htl.control.TeamRepository;
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

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance teamList(List<Team> teams);
        public static native TemplateInstance addTeam(boolean failed);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAllTeams(){
        //List<Team> all = teamRepo.listAll();
        return Templates.teamList(
                teamRepo.getAllSorted()
        );
    }

    @GET
    @Path("/addTeam")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance addTeam(){
        return Templates.addTeam(false);
    }

    @Path("/addTeam")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create(
            @Context UriInfo uriInfo
            , @FormParam("name") String name
            , @FormParam("abbr") String abbr
    ) {
        if (name.equals("") || abbr.equals("")) {

            Templates.addTeam(true);
            return Response.status(301)
                    .location(URI.create("/teams/addTeam"))
                    .build();
        }
        else {
            try {
                Team newTeam = new Team(name, abbr);
                teamRepo.persist(newTeam);
            } catch (Exception e) {
                log.error("Exception '" + e.getMessage() + "' raised");

                Templates.addTeam(true);
                return Response.status(301)
                        .location(URI.create("/teams/addTeam"))
                        .build();
            }

            return Response.status(301)
                    .location(URI.create("/dashboard"))
                    .build();
        }
    }

}
