package bowling.domain;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Objects;

public class NormalFrame extends AbstractFrame {

    public static final int LAST_NORMAL_FRAME_NUMBER = 9;

    private Frame nextNormalFrame;

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    public Frame bowl(int numberOfPins) {
        BowlResult bowlResult = frameBowl.bowl(numberOfPins);
        return isCompleted(bowlResult) ? nextStep() : this;
    }

    public boolean isCompleted(BowlResult bowlResult) {
        return !bowlResult.equals(BowlResult.NONE);
    }

    private Frame nextStep() {
        if (frameNumber == LAST_NORMAL_FRAME_NUMBER) {
            return null;
        }
        nextNormalFrame = new NormalFrame(frameNumber + 1);
        return nextNormalFrame;
    }

    @Override
    public Frame getNextFrame() {
        return nextNormalFrame;
    }

    @Override
    public Iterator<Frame> iterator() {
        final Frame me = this;
        return new Iterator<Frame>() {

            int frameNumber = 0;
            Frame frame = me;

            @Override
            public boolean hasNext() {
                if (frameNumber == 0) {
                    return true;
                }
                return Objects.nonNull(frame.getNextFrame());
            }

            @Override
            public Frame next() {
                if (frameNumber == 0) {
                    frameNumber++;
                    return frame;
                }
                for (int i = 0; i < frameNumber; i++) {
                    frame = frame.getNextFrame();
                }
                return frame;
            }

        };
    }

    @Override
    public String toString() {
        return frameBowl.getBowlCount() == 0 ? "" : MessageFormat.format("{0}:{1}", frameNumber, frameBowl.getTotalNumberOfPins());
    }

}
