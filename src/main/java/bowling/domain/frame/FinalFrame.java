package bowling.domain.frame;

import bowling.domain.bowl.FinalBowl;

import java.util.Iterator;

public class FinalFrame extends AbstractFrame {

    private final FinalBowl finalBowl = new FinalBowl();

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        finalBowl.bowl(numberOfPin);
        return this;
    }

    public boolean isCompleted() {
        return finalBowl.isCompleted();
    }

    @Override
    public boolean isEnd() {
        return isCompleted();
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return finalBowl.format();
    }

}
