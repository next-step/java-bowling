package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.user.User;

public class ScoreBoard {

    private final User user;
    private final Frames frames;

    private ScoreBoard(User user, Frames frames) {
        this.user = user;
        this.frames = frames;
    }

    public static ScoreBoard init(User user, Frames frames) {
        return new ScoreBoard(user, frames);
    }

    public void bowl(Pin felledPin) {
        frames.bowl(felledPin);
    }

    public User getUser() {
        return user;
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }

    public Index getLastIndex() {
        return frames.getLastIndex();
    }

    public Frame getFirstFrame() {
        return this.frames.getFirstFrame();
    }

}
