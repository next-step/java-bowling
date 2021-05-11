package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.exception.NameNullPointerException;

import java.util.Objects;

public final class Player {

    private final Name name;
    private final Frames frames;

    public static final Player from(final Name name) {
        return new Player(name);
    }

    public Player(final Name name) {
        validateNull(name);
        this.name = name;
        this.frames = Frames.initialize();
    }

    private final void validateNull(final Name name) {
        if (Objects.isNull(name)) {
            throw new NameNullPointerException();
        }
    }

}
