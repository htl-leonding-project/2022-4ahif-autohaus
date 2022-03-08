package at.htl.filewriter;

import at.htl.entity.Tournament;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import org.jboss.logging.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class FilewriterGP {


    private static final Logger LOG = Logger.getLogger("FileWriter");

    private static final String _origin = "asciidocs/plantuml";
    private static final String _target = "asciidocs/images/generated-diagrams/";
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

            convertToPNG(new File(_origin+"/ResultGP.puml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertToPNG(File source){
        try {
            SourceFileReader reader = new SourceFileReader(source);
            List<GeneratedImage> list = reader.getGeneratedImages();
            File png = list.get(0).getPngFile();

            png.renameTo(new File(_target+png.getName()));

            if(png.createNewFile()) {
                LOG.info(String.format("new file %s created", png.getName()));
            }else
                LOG.error("File already exists");

            png.delete();
        }catch(IOException e){
            LOG.error("no file to convert!");
        }
    }

}
