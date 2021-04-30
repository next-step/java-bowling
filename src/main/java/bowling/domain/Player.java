package bowling.domain;

public final class Player {

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static final Player from(final String name) {
        return new Player(name);
    }
}
