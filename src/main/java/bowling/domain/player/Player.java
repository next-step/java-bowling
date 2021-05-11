package bowling.domain.player;

import bowling.domain.frame.Frames;

public final class Player {

    private final Name name;
    private final Frames frames;

    public Player(final Name name) {
        this.name = name;
        this.frames = Frames.initialize();
    }

    public static final Player from(final Name name) {
        return new Player(name);
    }
}
