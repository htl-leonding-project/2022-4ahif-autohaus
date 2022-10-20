package at.htl.boundary;

import at.htl.control.MatchRepository;
import at.htl.control.NodeRepository;
import at.htl.entity.Match;
import at.htl.entity.Node;
import at.htl.entity.Team;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.hibernate.annotations.UpdateTimestamp;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("matches")
public class MatchResource {

    @Inject
    MatchRepository matchRepository;

    @Inject
    NodeRepository nodeRepository;

    @Inject
    Logger log;

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
    @Transactional
    public Response getAll()
    {
        List<Match> matches = matchRepository.listAll();
        return Response.ok(matches).build();
    }

    @GET
    @Path(value = "amount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response amount(){
        return Response.ok(matchRepository.listAll().size()).build();
    }

    /**
     * Gibt ein Match per id zurück
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
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
    @Path("/team1/{team1}" )
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
    @Path("/create")
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
    @Path("/delete/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id){
        boolean deleted = matchRepository.deleteById(id);
        if (deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/matchResult/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance matchResult(@PathParam("id") Long id)
    {
        return Templates.matchResult(matchRepository.findById(id));
    }

    @Path("/playMatch/{id}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance playMatch(@PathParam("id") Long id){
        return MatchResource.Templates.playMatch(matchRepository.findById(id));
    }

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Match match){
        Node current = nodeRepository.getNodeByMatch(match);
        current.getCurMatch().setPointsTeam1(match.pointsTeam1);
        current.getCurMatch().setPointsTeam2(match.pointsTeam2);
        if(current.getParentNode() != null) {
            if(current.getParentNode().areChildrenComplete()){
                current.getParentNode().setChildMatchWinners();
            }
        }
        else{
            current.getCurMatch().getWinningTeam().incrementWinAmount();
        }

        return Response.ok().build();
    }

}











