package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.user.User;

public class ScoreBoard {

    private final User user;
    private final Frame firstFrame;
    private Frame currentFrame;

    private ScoreBoard(User user) {
        this.user = user;
        this.firstFrame = Frame.init();
        this.currentFrame = firstFrame;
    }

    public static ScoreBoard init(User user) {
        return new ScoreBoard(user);
    }

    public void bowl(Pin felledPin) {
        currentFrame = currentFrame.bowl(felledPin);
    }

    public User getUser() {
        return user;
    }

    public boolean isGameOver() {
        return currentFrame.isEnd();
    }

    public Frame getFirstFrame() {
        return firstFrame;
    }

    public Index getLastIndex() {
        return currentFrame.getIndex();
    }

}
