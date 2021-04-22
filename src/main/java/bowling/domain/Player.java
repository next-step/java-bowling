package bowling.domain;

public class Player {
    private final String name;

    public static Player of(String name) {
        return new Player(name);
    }

    Player(String name) {
        this.name = name;
    }
}
