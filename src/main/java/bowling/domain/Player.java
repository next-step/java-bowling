package bowling.domain;

public class Player {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player getInstance(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }
}
