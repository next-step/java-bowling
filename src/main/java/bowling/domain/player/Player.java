package bowling.domain.player;

public class Player {
    private static final int LIMIT_LENGTH_OF_NAME = 3;

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(name);
    }
}
