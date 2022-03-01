package at.htl.boundary;

import at.htl.controller.MatchRepository;
import at.htl.entity.Match;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/matches")
public class MatchResource {
    /**
     * Gibt alle Matches zurück
     * @return
     */
    @Inject
    MatchRepository matchRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        List<Match> matches = matchRepository.listAll();
        return Response.ok(matches).build();
    }

    /**
     * Gibt ein Match per id zurück
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return matchRepository.findByIdOptional(id)
                .map(ma -> Response.ok(ma).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Diese Methode gibt Matches mit einem bestimmten Team zurück
     * @param team1
     * @return
     */
    @GET
    @Path("team1/{team1}" )
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchPerTeam(@PathParam("team1") String team1)
    {
        return Response
                .ok(matchRepository.list("Select m from match where m.team1 = ?1 order by id",
                        "Desc",team1))
                .build();
    }

    /**
     * Mithilfe dieser Methode kann man ein Match hinzufügen
     * @param match
     * @return
     */
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMatch(Match match){
        matchRepository.persist(match);
        if (matchRepository.isPersistent(match)){
            return Response.created(URI.create("/matches"+match.getId())).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Diese Methode löscht Matches nach der Id.
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id){
        boolean deleted = matchRepository.deleteById(id);
        if (deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


}











