package bowling.domain.result;

import bowling.domain.frame.Frame;
import bowling.domain.value.Frames;
import bowling.domain.value.Pins;

public class Result {

    private static final int FINAL_FRAME_NO = 10;

    private final Frames frames;

    private Result(Frames frames) {
        this.frames = frames;
    }

    public static Result from(Frames frames) {
        return new Result(frames);
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
