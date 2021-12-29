package bowling.domain.value;

import bowling.domain.frame.Frame;

import java.util.List;

public class Frames {
    private static final String  NULL_ERROR_MSG = "frames은 필수값입니다.";

    private int currentFrameNumber;
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        if(frames == null) {
            throw new IllegalArgumentException(NULL_ERROR_MSG);
        }

        this.frames = frames;
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public void bowl(Pins pins) {
        Frame frame = getCurrentFrame();
        frame.bowl(pins);
        checkFrameOver(frame);
    }

    private void checkFrameOver(Frame frame) {
        if (frame.isFrameOver()) {
            currentFrameNumber++;
        }
    }

    public Frame getCurrentFrame() {
        return frames.get(currentFrameNumber);
    }

    public boolean isGameOver() {
        return frames.get(frames.size() - 1).isGameOver();
    }

    public int currentFrameNumber() {
        if (isGameOver() || getCurrentFrame().isFrameOver()) {
            return currentFrameNumber + 1;
        }

        return currentFrameNumber;
    }

    public Frame getFrame(int frameNumber) {
        return frames.get(frameNumber - 1);
    }
}
