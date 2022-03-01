package at.htl.filewriter;

import at.htl.entity.Tournament;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class FilewriterGP {

    public void writeResults(Tournament tournament){

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
                    .map(p -> p.getGPNodes())
                    .collect(Collectors.toList())
                    .stream().map(nL -> nL.stream().map(n -> n.getMatchTableGP()).collect(Collectors.joining("\n")))
                    .collect(Collectors.joining());

            content += tournament.GPGetConnections();

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
