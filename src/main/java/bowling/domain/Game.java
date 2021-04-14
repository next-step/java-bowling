package bowling.domain;

import bowling.domain.frame.GameFrames;

public class Game implements Endable, BallThrowable {
    private int frameCount;
    private GameFrames gameFrames;

    public Game() {
        this.frameCount = 1;
        this.gameFrames = new GameFrames();
    }

    public int frameCount() {
        return this.frameCount;
    }

    @Override
    public void throwBall(int point) {
        gameFrames.throwBall(point);
        frameCount = gameFrames.frameCount();
    }

    @Override
    public boolean ended() {
        return gameFrames.ended();
    }

    public GameFrames gameFrames() {
        return gameFrames;
    }
}
