package bowling.service;

import bowling.domain.engine.Frame;
import bowling.domain.frame.Frames;

import java.util.List;

public class BowlingGame {

    private final Frames frames;

    public BowlingGame() {
        this.frames = new Frames();
    }

    public void bowl(final int pins) {
        frames.bowl(pins);
    }

    public List<Frame> frames() {
        return frames.values();
    }

    public boolean isLast() {
        return frames.isLastFrame();
    }

    public int frameNo() {
        return frames.values().size();
    }
}
