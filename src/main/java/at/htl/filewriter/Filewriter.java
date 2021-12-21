package at.htl.filewriter;

import at.htl.entity.Node;
import at.htl.entity.Tournament;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class Filewriter {

    File file = new File("asciidocs/plantuml/Result.puml");

    public void writeResult(String team01, String team02, int[] result){

        String resStr = result[0] + ":" + result[1];
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
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

    public void writeFinalResult(Node finalNode, Tournament t){

        Node currentNode = finalNode;
        Stack<Node> nodeStack = new Stack<>();
        try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
                writer.write("@startmindmap\n title "+t.getName()+"\n");
                while(currentNode != null || nodeStack.size() > 0) {
                    while (currentNode != null) {
                        writer.write(constructString(currentNode));
                        nodeStack.push(currentNode);
                        currentNode = currentNode.getLeftNode();
                    }
                    currentNode = nodeStack.pop();
                    currentNode = currentNode.getRightNode();
                }
                writer.write("caption Winner is: "+finalNode.getCurMatch().getWinningTeam().getName() + "\n");
                writer.write("@endmindmap\n");
                writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeIntermediateResult(Node node, Tournament t){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
            writer.write("@startmindmap\n title "+t.getName()+"\n");
            writer.write("- "+node.getCurMatch().getMatchResultString()+"\n");
            writer.write("-- "+node.getLeftNode().getCurMatch().getMatchResultString()+"\n");
            writer.write("-- "+node.getRightNode().getCurMatch().getMatchResultString()+"\n");
            writer.write("caption Winner is: "+node.getCurMatch().getWinningTeam().getName() + "\n");
            writer.write("@endmindmap\n");
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
}
