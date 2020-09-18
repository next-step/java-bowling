package bowling.domain.frame;

import bowling.domain.bowl.Bowls;

import java.util.Iterator;

public class FinalFrame extends AbstractFrame {

    private final Bowls bowls = new Bowls();

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        bowls.bowl(numberOfPin);
        return isCompleted() ? null : this;
    }

    public boolean isCompleted() {
        return bowls.isCompleted();
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return bowls.format();
    }

}
