package bowling.domain;

import java.text.MessageFormat;
import java.util.Objects;

public class NormalFrame {

    public static final int LAST_FRAME_NUMBER = 10;

    private final int frameNumber;
    private final FrameBowl frameBowl = new FrameBowl();

    private NormalFrame nextNormalFrame;

    public NormalFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public NormalFrame bowl(int numberOfPins) {
        BowlResult bowlResult = frameBowl.bowl(numberOfPins);
        return bowlResult.isCompleted() ? createNextFrame() : this;
    }

    private NormalFrame createNextFrame() {
        if (frameNumber == LAST_FRAME_NUMBER) {
            return null;
        }
        nextNormalFrame = new NormalFrame(frameNumber + 1);
        return nextNormalFrame;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public boolean hasNext() {
        return Objects.nonNull(nextNormalFrame);
    }

    public NormalFrame next() {
        return nextNormalFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
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
