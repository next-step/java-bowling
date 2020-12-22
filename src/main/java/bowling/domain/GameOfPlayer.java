package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class GameOfPlayer {
    private final Player player;
    private Frame currentFrame;

    public GameOfPlayer(Player player) {
        this.player = player;
        currentFrame = new NormalFrame(1);
    }

    public Frame getCurrentFrame() {
        return currentFrame;
    }

    public boolean playFrame(int count, Frame frame) {
        currentFrame = currentFrame.bowl(count);
        return frame != currentFrame;
    }

    public boolean isGameEnd() {
        return currentFrame.isGameEnd();
    }

    public Player getPlayer() {
        return player;
    }

}
