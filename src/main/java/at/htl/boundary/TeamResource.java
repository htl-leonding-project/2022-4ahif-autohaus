package at.htl.boundary;

import at.htl.controller.TeamRepository;
import at.htl.entity.Team;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teams")
public class TeamResource {

    @Inject
    TeamRepository teamRepo;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance teamList(List<Team> teams);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getAllTeams(){
        List<Team> all = teamRepo.listAll();
        return Templates.teamList(
                teamRepo.findAll().list()
        );
    }
}
