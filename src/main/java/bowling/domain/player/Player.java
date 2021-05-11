package bowling.domain.player;

import bowling.domain.frame.Frames;
import bowling.domain.score.Scores;
import bowling.domain.state.Pins;
import bowling.exception.NameNullPointerException;

import java.util.Objects;

public final class Player {

    private final Name name;
    private final Frames frames;

    public static final Player from(final String name) {
        return from(Name.valueOf(name));
    }

    public static final Player from(final Name name) {
        return new Player(name);
    }

    private Player(final Name name) {
        validateNull(name);
        this.name = name;
        this.frames = Frames.initialize();
    }

    private final void validateNull(final Name name) {
        if (Objects.isNull(name)) {
            throw new NameNullPointerException();
        }
    }

    public final boolean isFinish() {
        return frames.isFinish();
    }

    public final void bowl(final Pins fallPins) {
        frames.bowl(fallPins);
    }

    public final int sequence() {
        return frames.sequence();
    }

    public final String name() {
        return name.name();
    }

    public final Scores scores() {
        return frames.scores();
    }
}
