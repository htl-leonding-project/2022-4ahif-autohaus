package at.htl.boundary;

import at.htl.controller.TournamentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import at.htl.entity.Tournament;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.CheckedTemplate;

@RequestScoped
@Path("tournament")
public class TournamentResource {

    @Inject
    TournamentRepository tournamentRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance tournament(Tournament tournament);
        public static native TemplateInstance error(String msg);
        public static native TemplateInstance create();
    }

    @GET
    @Path("{id}")
    public TemplateInstance get(@PathParam("id") long id) {
        Tournament tournament = tournamentRepository.findById(id);
        return Templates.tournament(tournament);
    }

    @GET
    @Path("create")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance create() {
        return Templates.create();
    }

    @GET
    @Path("error")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance showError(String message) {
        return Templates.error(message);
    }

}
