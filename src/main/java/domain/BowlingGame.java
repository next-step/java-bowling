package domain;

import domain.frame.FrameFactory;
import domain.frame.Frames;

public class BowlingGame {

    private Frames frames;

    public BowlingGame() {
        frames = FrameFactory.createFrames();
    }

    public Frames bowl(Pins downPins) {
        frames = frames.bowl(downPins);
        return frames;
    }

    public boolean isRunning() {
        return !frames.isFinished();
    }
}
