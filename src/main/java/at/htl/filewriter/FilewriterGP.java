package at.htl.filewriter;

import at.htl.entity.PhaseGP;
import at.htl.entity.TournamentGP;

import java.io.File;
import java.util.List;

public class FilewriterGP {

    File file = new File("asciidocs/plantuml/ResultGP.puml");

    public void writeResults(TournamentGP tournament){
        List<PhaseGP> phases = tournament.getPhases();

        for (PhaseGP phase : phases) {

        }
    }

}
