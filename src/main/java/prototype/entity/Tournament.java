package prototype.entity;

public class Tournament {

    //erst einmal leer lassen
    String name;

    public Tournament(String name) {
        this.name = name;
    }

    public Tournament() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
