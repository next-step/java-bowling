package bowling.domain;

import java.text.MessageFormat;
import java.util.Objects;

public class Frame {

    public static final int LAST_FRAME_NUMBER = 10;

    private final int frameNumber;
    private final FrameBowl frameBowl = new FrameBowl();

    private Frame nextFrame;

    public Frame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public Frame bowl(int numberOfPins) {
        BowlResult bowlResult = frameBowl.bowl(numberOfPins);
        return bowlResult.isCompleted() ? createNextFrame() : this;
    }

    private Frame createNextFrame() {
        if (frameNumber == LAST_FRAME_NUMBER) {
            return null;
        }
        nextFrame = new Frame(frameNumber + 1);
        return nextFrame;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public boolean hasNext() {
        return Objects.nonNull(nextFrame);
    }

    public Frame next() {
        return nextFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame that = (Frame) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }

    @Override
    public String toString() {

        return frameBowl.getBowlCount() == 0 ? "" : MessageFormat.format("{0}:{1}", frameNumber, frameBowl.getTotalNumberOfPins());
    }

}
