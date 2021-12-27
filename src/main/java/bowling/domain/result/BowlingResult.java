package bowling.domain.result;

import bowling.domain.frame.Frame;
import bowling.domain.value.Frames;
import bowling.domain.value.Pins;

public class BowlingResult {

    private static final int FINAL_FRAME_NO = 10;

    private final Frames frames;

    private BowlingResult(Frames frames) {
        this.frames = frames;
    }

    public static BowlingResult from(Frames frames) {
        return new BowlingResult(frames);
    }

    public void bowl(Pins pins) {
        frames.bowl(pins);
    }

    public boolean isFrameOver(int playFrameNumber) {
        return currentFrameNumber() != playFrameNumber;
    }

    public int currentFrameNumber() {
        return frames.currentFrameNumber();
    }

    public boolean isGameOver() {
        return frames.isGameOver();
    }

    public Frame getFrames(int frameNumber) {
        return frames.getFrame(frameNumber);
    }
}