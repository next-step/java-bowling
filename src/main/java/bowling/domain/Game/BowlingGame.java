package bowling.domain.Game;

import bowling.domain.frame.Frames;
import bowling.domain.user.User;

import java.util.Objects;

public class BowlingGame {
    private final User user;
    private final Frames frames;

    private BowlingGame(User user, Frames frames) {
        this.user = user;
        this.frames = frames;
    }

    public static BowlingGame readyGame(User user) {
        return new BowlingGame(user, Frames.readyFrames());
    }

    public static BowlingGame of(User user, Frames frames) {
        return new BowlingGame(user, frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(user, that.user) && Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, frames);
    }
}
