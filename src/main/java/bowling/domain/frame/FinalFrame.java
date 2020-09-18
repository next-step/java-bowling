package bowling.domain.frame;

import bowling.domain.bowl.NormalBowls;

import java.util.Iterator;

public class FinalFrame extends AbstractFrame {

    private final NormalBowls normalBowls = new NormalBowls();

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        normalBowls.bowl(numberOfPin);
        return isCompleted() ? null : this;
    }

    public boolean isCompleted() {
        return normalBowls.isCompleted();
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return normalBowls.format();
    }

}
