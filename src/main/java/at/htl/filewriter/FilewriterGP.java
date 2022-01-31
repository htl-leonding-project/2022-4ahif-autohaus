package at.htl.filewriter;

import at.htl.entity.GruppeGP;
import at.htl.entity.NodeGP;
import at.htl.entity.PhaseGP;
import at.htl.entity.TournamentGP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FilewriterGP {

    public void writeResults(TournamentGP tournament){

        try{
            FileWriter file = new FileWriter("asciidocs/plantuml/ResultGP.puml");

            String content =
                    """
                    @startuml
                    left to right direction
                    
                    """;

            content += tournament
                    .getGroups()
                    .stream()
                    .map(g -> g.getGroupMap())
                    .collect(Collectors.joining("\n"));

            content += tournament
                    .getPhases()
                    .stream()
                    .map(p -> p.getNodes())
                    .collect(Collectors.toList())
                    .stream().map(nL -> nL.stream().map(n -> n.getMatchTable()).collect(Collectors.joining("\n")))
                    .collect(Collectors.joining());

            content += """
                    
                    @enduml
                    """;

            file.write(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
