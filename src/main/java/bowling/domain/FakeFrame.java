package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;
import bowling.exception.NoMoreCountingActionException;
import bowling.exception.NoMoreFinishActionsException;
import bowling.exception.NoMoreIndexActionsException;
import bowling.util.BowlingFixture;

public final class FakeFrame implements Frame {

    private FakeFrame() {
    }

    public static final Frame initialize() {
        return new FakeFrame();
    }

    @Override
    public final Frame bowl(final HitCount hitCOunt) {
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
        return BowlingFixture.ZERO;
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
