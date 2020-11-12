package bowling.domain;

public class Player {
    private static final int LENGTH_LIMIT = 3;

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player from(String name) {
        validateLength(name);
        return new Player(name);
    }

    private static void validateLength(String name) {
        if(name.length() > LENGTH_LIMIT) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }
}
