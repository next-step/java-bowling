package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class GameOfPlayer {
    private final Player player;
    private final Frame firstFrame;
    private Frame currentFrame;

    public GameOfPlayer(Player player) {
        this.player = player;
        firstFrame = new NormalFrame(1);
        currentFrame = firstFrame;
    }

    public void bowl(int count) {
        currentFrame = currentFrame.bowl(count);
    }

    public boolean isGameEnd() {
        return currentFrame.isGameEnd();
    }

    public Player getPlayer() {
        return player;
    }

    public Frame getFirstFrame() {
        return firstFrame;
    }
}
