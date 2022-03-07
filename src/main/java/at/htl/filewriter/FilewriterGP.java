package at.htl.filewriter;

import at.htl.entity.Tournament;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FilewriterGP {

    private static final Logger LOG = Logger.getLogger("FileWriter");

    public void writeResults(Tournament tournament){

        try{
            FileWriter file = new FileWriter("asciidocs/plantuml/ResultGP.puml");

            String content = String.format(
                    """
                    @startuml %s.png
                    left to right direction
                    
                    """, tournament.getName());

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

            convertToPNG(new File("asciidocs/plantuml/ResultGP.puml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToPNG(File source){
        try {
            SourceFileReader reader = new SourceFileReader(source);
            List<GeneratedImage> list = reader.getGeneratedImages();
            File png = list.get(0).getPngFile();
            if(png.createNewFile())
                LOG.info(String.format("new file %s created",png.getName()));
            else
                LOG.info("File already exists");
        }catch(IOException e){
            LOG.info("no file to convert!");
        }
    }

}
