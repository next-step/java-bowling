package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingGame {
    private Frames frames;

    public BowlingGame(Player player) {
        this.frames = new Frames(player);
    }

    public Frame next() {
        return this.frames.getNextFrame();
    }

    public Frames getFrames() {
        return frames;
    }
}
