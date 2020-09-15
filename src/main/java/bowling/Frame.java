package bowling;

import java.util.Objects;

public class Frame {

    private final int frame;
    private final Frame nextFrame;

    private Frame(int frame, int totalFrames) {
        this.frame = frame;
        this.nextFrame = Frame.of(frame + 1, totalFrames);
    }

    public int getFrame() {
        return this.frame;
    }

    public Frame getNextFrame() {
        return this.nextFrame;
    }

    public void bowl(int falledPins) {
    }

    public boolean isFinish() {
        return true;
    }

    public boolean isLastFrame() {
        return Objects.isNull(nextFrame);
    }

    public static Frame of(int frame, int totalFrames) {
        if (frame > totalFrames) {
            return null;
        }
        return new Frame(frame, totalFrames);
    }
}
