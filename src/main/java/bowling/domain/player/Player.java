package bowling.domain.player;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Scores;
import bowling.domain.state.Pins;
import bowling.exception.NameNullPointerException;

import java.util.Objects;

public final class Player {

    private final Name name;
    private final Frames frames;

    public Player(final String name) {
        this(new Name(name), new Frames());
    }

    public Player(final Name name) {
        this(name, new Frames());
    }

    public Player(final Name name, final Frames frames) {
        validateNull(name);
        this.name = name;
        this.frames = frames;
    }

    private final void validateNull(final Name name) {
        if (Objects.isNull(name)) {
            throw new NameNullPointerException();
        }
    }

    public final boolean isFinish() {
        return frames.isFinish();
    }

    public final void bowl(final int fallPins) {
        bowl(Pins.valueOf(fallPins));
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

    public final Frames frames() {
        return frames;
    }

    public final Frame current() {
        return frames.current();
    }
}
