package bowling.domain;

import bowling.domain.frame.Frames;

public class Game {

    private Frames frames;

    public Game() {
        this.frames = new Frames();
    }

    public void throwBall(int point) {
        frames.throwBall(point);
    }

    public boolean ended() {
        return frames.ended();
    }

    public int frameCount() {
        return frames.frameCount();
    }

    public Frames getFrames() {
        return frames;
    }
}
