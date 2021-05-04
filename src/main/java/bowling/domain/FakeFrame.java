package bowling.domain;

import bowling.domain.state.PinCount;
import bowling.exception.*;

public final class FakeFrame implements Frame {

    private FakeFrame() {
    }

    public static final Frame initialize() {
        return new FakeFrame();
    }

    @Override
    public final Frame bowl(final PinCount hitCount) {
        throw new NoMoreBowlActionsException();
    }

    @Override
    public final boolean isFinish() {
        throw new NoMoreFinishActionsException();
    }

    @Override
    public final int index() {
        throw new NoMoreIndexActionsException();
    }

    @Override
    public final int size() {
        throw new NoMoreGetSizeActionsException();
    }

    @Override
    public final int firstCount() {
        throw new NoMoreCountingActionException();
    }

    @Override
    public final int secondCount() {
        throw new NoMoreCountingActionException();
    }

    @Override
    public final int thirdCount() {
        throw new NoMoreCountingActionException();
    }
}
