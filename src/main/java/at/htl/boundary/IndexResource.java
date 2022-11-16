package at.htl.boundary;

import at.htl.control.GroupRepository;
import at.htl.control.MatchRepository;
import at.htl.control.TeamRepository;
import at.htl.control.TournamentRepository;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
@Path("/")
public class IndexResource {

    @Inject
    TournamentRepository tournamentRepository;

    @Inject
    TeamRepository teamRepository;

    @Inject
    GroupRepository groupRepository;

    @Inject
    MatchRepository matchRepository;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance dashboard(long nrOfTournaments,
                                                    long nrOfTeams,
                                                    long nrOfGroups,
                                                    long nrOfMatches);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getStats(){

        long nrOfTournaments = tournamentRepository.listAll().size();

        long nrOfTeams = teamRepository.listAll().size();
        long nrOfGroups = groupRepository.listAll().size();
        long nrOfMatches = matchRepository.listAll().size();

        return Response.ok(IndexResource.Templates.dashboard(
                nrOfTournaments,
                nrOfTeams,
                nrOfGroups,
                nrOfMatches)).build();
    }
}
