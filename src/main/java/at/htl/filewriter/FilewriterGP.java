package at.htl.filewriter;

import at.htl.entity.GruppeGP;
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
