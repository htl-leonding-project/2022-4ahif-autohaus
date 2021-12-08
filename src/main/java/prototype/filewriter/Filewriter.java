package prototype.filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filewriter {

    public void writeResult(String team01, String team02, int[] result){
        File file = new File("asciidocs/plantuml/Result.puml");
        String resStr = result[0] + ":" + result[1];
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(
                    "@startsalt\n" +
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
    }
}
