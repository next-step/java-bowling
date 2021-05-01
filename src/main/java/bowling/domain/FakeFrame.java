package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;
import bowling.exception.NoMoreFinishActionsException;
import bowling.exception.NoMoreIndexActionsException;

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
}
