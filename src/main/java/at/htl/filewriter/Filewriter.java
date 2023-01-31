package at.htl.filewriter;

import at.htl.entity.Node;
import at.htl.entity.Tournament;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

@ApplicationScoped
public class Filewriter {

    private static final Logger LOG = Logger.getLogger("FileWriter");

    //@ConfigProperty(name = "filewriter.origin")
    private String ORIGIN = "asciidocs/plantuml/Result.puml";

    private String ORIGIN_PATH = "asciidocs/plantuml/";

    //@ConfigProperty(name = "filewriter.target")
    private String TARGET = "asciidocs/images/generated-diagrams/";
    @ConfigProperty(name = "app.img.directory", defaultValue = "leoCompetition/src/assets/images/generated-diagrams/")
    public String TARGET_FOR_WEB;

    /*public void writeResult(String team01, String team02, int[] result){

        String resStr = result[0] + ":" + result[1];
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ORIGIN,true));
            writer.write(
                    "@startsalt \n" +
            "{\n" +
                "{T\n" +
                    "+ Tournament\n" +
                    "++ " + team01 + " vs. " + team02 + " " + resStr + "\n" +
                "}\n" +
            "}\n" +
            "@endsalt\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void writeFinalResult(Node finalNode, Tournament t){

        Node currentNode = finalNode;
        Stack<Node> nodeStack = new Stack<>();
        try{
            Path originPath = Paths.get(ORIGIN_PATH);
            if(!Files.exists(originPath)) {
                Files.createDirectories(Paths.get(ORIGIN_PATH));
            }
            Path targetPath = Paths.get(TARGET);
            if(!Files.exists(targetPath)) {
                Files.createDirectories(Paths.get(TARGET));
            }
            System.out.println(TARGET_FOR_WEB);
            Path webTargetPath = Paths.get(TARGET_FOR_WEB);
            if(!Files.exists(webTargetPath)) {
                Files.createDirectories(Paths.get(TARGET_FOR_WEB));
            }
            FileWriter file = new FileWriter(ORIGIN);

            String content = String.format("""
                @startmindmap %s.png
                    <style>
                    mindmapDiagram{
                        node{
                            BackgroundColor White
                            
                        }
                        LineColor Black
                    }
                    </style>
                title %s
                """, t.getName(), t.getName()
            );

            while(currentNode != null || nodeStack.size() > 0) {
                while (currentNode != null) {
                    content+= "\n"+constructString(currentNode);
                    nodeStack.push(currentNode);
                    currentNode = currentNode.getLeftNode();
                }
                currentNode = nodeStack.pop();
                currentNode = currentNode.getRightNode();
            }
            content+= String.format("""
                caption Winner is: %s 
                @endmindmap
                """,finalNode.getCurMatch().getWinningTeam().getName()
            );

            file.write(content);
            file.close();

            convertToPNG(new File(ORIGIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeIntermediateResult(Node node, Tournament t){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(ORIGIN,true));

            writer.write(String.format(
                    """
                    @startmindmap
                    title %s
                    - %s
                    -- %s
                    -- %s
                    caption Winner is: %s
                    @endmindmap
                                            
                    """,
                    t.getName(),
                    node.getCurMatch().getMatchResultString(),
                    node.getLeftNode().getCurMatch().getMatchResultString(),
                    node.getRightNode().getCurMatch().getMatchResultString(),
                    node.getCurMatch().getWinningTeam().getName()
            ));

            //writer.write("@startmindmap \n title "+t.getName()+"\n");
            //writer.write("- "+node.getCurMatch().getMatchResultString()+"\n");
            //writer.write("-- "+node.getLeftNode().getCurMatch().getMatchResultString()+"\n");
            //writer.write("-- "+node.getRightNode().getCurMatch().getMatchResultString()+"\n");
            //writer.write("caption Winner is: "+node.getCurMatch().getWinningTeam().getName() + "\n");
            //writer.write("@endmindmap\n");
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String constructString(Node node){
        String line = "";
        for (int i = 0; i < node.getPhase().getLevel(); i++){
            line += "-";
        }

        return line += " "+node.getCurMatch().getMatchResultString()+"\n";

    }

    public void convertToPNG(File source){
        try {
            SourceFileReader reader = new SourceFileReader(source);
            List<GeneratedImage> list = reader.getGeneratedImages();
            File png = list.get(0).getPngFile();
            //File old = new File(TARGET+png.getName());
            File old = new File(TARGET_FOR_WEB+png.getName());
            old.delete();
            old = new File(TARGET+png.getName());
            old.delete();

            png.renameTo(new File(TARGET_FOR_WEB+png.getName()));
            if(png.createNewFile()) {
                LOG.info(String.format("new file %s created", png.getName()));
            }

            old = new File(ORIGIN_PATH+png.getName());
            old.delete();
            //FileUtils.copyFile(new File(TARGET+png.getName()), new File(TARGET_FOR_WEB+png.getName()));
        }catch(IOException e){
            LOG.error("no file to convert!");
        }
    }
}
