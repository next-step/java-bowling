package bowling.domain;

import java.text.MessageFormat;
import java.util.Iterator;

public class FinalFrame extends AbstractFrame {

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPins) {
        BowlResult bowlResult = frameBowl.bowl(numberOfPins);
        return isCompleted(bowlResult) ? null : this;
    }

    public boolean isCompleted(BowlResult bowlResult) {
        return !bowlResult.equals(BowlResult.NONE);
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return frameBowl.getBowlCount() == 0 ? "" : MessageFormat.format("{0}:{1}", frameNumber, frameBowl.getTotalNumberOfPins());
    }

}
