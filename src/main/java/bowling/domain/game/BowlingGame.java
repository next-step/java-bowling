package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.frame.Pin;
import bowling.domain.frame.Round;
import bowling.domain.result.GameResult;
import bowling.domain.user.User;

import java.util.Objects;

public class BowlingGame {
    private final User user;
    private Frames frames;

    private BowlingGame(User user, Frames frames) {
        this.user = user;
        this.frames = frames;
    }

    public static BowlingGame readyGame(User user) {
        return of(user, Frames.readyFrames());
    }

    public static BowlingGame of(User user, Frames frames) {
        return new BowlingGame(user, frames);
    }

    public void bowl(Pin pin) {
        frames.bowl(pin);
    }

    public GameResult createResult() {
        return new GameResult(user, frames.createResults());
    }

    public String userName() {
        return user.getName();
    }

    public Round round() {
        return frames.round();
    }

    public boolean isGameEnd() {
        return frames.isGameEnd();
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

    @Override
    public String toString() {
        return "BowlingGame{" +
                "user=" + user +
                ", frames=" + frames +
                '}';
    }
}
