package at.htl.boundary;

import at.htl.control.MatchRepository;
import at.htl.entity.Match;
import at.htl.entity.Team;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/matches")
public class MatchResource {

    @Inject
    MatchRepository matchRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance matchResult(Match match);
        public static native TemplateInstance playMatch(Match match);
    }
    /**
     * Gibt alle Matches zurück
     * @return
     */
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

    @Path("/matchResult/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public Response create(
            @Context UriInfo uriInfo
            , @PathParam("id") Long id
            , @FormParam("team1") String team1
            , @FormParam("team2") String team2
    ) {
        if (team1.equals("") || team2.equals("")) {

            MatchResource.Templates.matchResult(null);
            return Response.status(301)
                    .location(URI.create("/matches/matchResult"))
                    .build();
        }
        else {
            Match match = matchRepository.findById(id);

            match.setPointsTeam1(Integer.parseInt(team1));
            match.setPointsTeam2(Integer.parseInt(team2));

            return Response.status(301)
                    .location(URI.create("/"))
                    .build();
        }
    }

    @Path("/playMatch")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance playMatch(Match match){
        return MatchResource.Templates.playMatch(match);
    }

}











