package at.htl.boundary;

import at.htl.controller.TournamentRepository;
import at.htl.entity.Tournament;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("api/tournament")
public class TournamentApi {

    @Inject
    TournamentRepository tournamentRepository;

    @Inject
    TournamentResource tournamentResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response
                .ok(tournamentRepository.findAll().list())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(
            @Context UriInfo uriInfo
            , @FormParam("name") String name
    ) {
        try {
            Tournament tournament = new Tournament(name);
            tournamentRepository.persist(tournament);
        } catch (Exception e) {
            return Response
                    .status(Response.Status.OK)
                    .entity(tournamentResource.showError(e.getMessage()))
                    .type(MediaType.TEXT_HTML)
                    .build();
        }
        return Response.status(301)
                .location(URI.create("/"))
                .build();
    }
}
