package at.htl.boundary;

import at.htl.control.MatchRepository;
import at.htl.control.NodeRepository;
import at.htl.control.TournamentRepository;
import at.htl.entity.Match;
import at.htl.entity.Node;
import at.htl.entity.Status;
import at.htl.entity.Tournament;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.transform.Templates;
import java.net.URI;
import java.util.List;

@Path("matches")
public class MatchResource {

    @Inject
    MatchRepository matchRepository;

    @Inject
    NodeRepository nodeRepository;

    @Inject
    TournamentRepository tournamentRepository;

    @Inject
    Logger log;
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
            return Response.created(URI.create("/c.handel/api/matches"+match.getId())).build();
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

    @Path("/update")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(Match match){
        Node current = nodeRepository.getNodeByMatch(match);
        Tournament newTournament = tournamentRepository
                .find("id = ?1", match.team1.getTournamentId())
                .firstResult();

        newTournament.setStatus(Status.IN_PROGRESS);
        tournamentRepository.persist(newTournament);

        current.getCurMatch().setPointsTeam1(match.pointsTeam1);
        current.getCurMatch().setPointsTeam2(match.pointsTeam2);
        if(current.getParentNode() != null) {
            if(current.getParentNode().areChildrenComplete()){
                current.getParentNode().setChildMatchWinners();
            }
        }

        return Response.ok().build();
    }

}











