package bowling.domain.Game;

import bowling.domain.frame.Frames;
import qna.domain.User;

public class BowlingGame {
    private final User user;
    private final Frames frames;

    private BowlingGame(User user, Frames frames) {
        this.user = user;
        this.frames = frames;
    }

    public static BowlingGame of(User user, Frames frames) {
        return new BowlingGame(user, frames);
    }
}
