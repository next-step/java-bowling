package bowling.domain;

import java.text.MessageFormat;

public class NormalFrame extends AbstractFrame {

    public static final int LAST_NORMAL_FRAME_NUMBER = 9;

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    @Override
    public Frame bowl(int numberOfPins) {
        BowlResult bowlResult = frameBowl.bowl(numberOfPins);
        return isCompleted(bowlResult) ? createNextFrame() : this;
    }

    private boolean isCompleted(BowlResult bowlResult) {
        return !bowlResult.equals(BowlResult.NONE);
    }

    private Frame createNextFrame() {
        nextNormalFrame = (frameNumber == LAST_NORMAL_FRAME_NUMBER) ? new FinalFrame() : new NormalFrame(frameNumber + 1);
        return nextNormalFrame;
    }

    @Override
    public String toString() {
        return frameBowl.getBowlCount() == 0 ? "" : MessageFormat.format("{0}:{1}", frameNumber, frameBowl.getTotalNumberOfPins());
    }

}
