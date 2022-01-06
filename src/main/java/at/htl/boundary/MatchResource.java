package at.htl.boundary;

import at.htl.entity.Match;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/matches")
public class MatchResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        List<Match> matches = Match.listAll();
        return Response.ok(matches).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return Match.findByIdOptional(id)
                .map(ma -> Response.ok(ma).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("team1/{team1}" )
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchPerTeam(@PathParam("team1") String team1)
    {
        return Response
                .ok(Match.list("Select m from match where m.team1 = ?1 order by id",
                "Desc",team1))
                .build();
    }

}










