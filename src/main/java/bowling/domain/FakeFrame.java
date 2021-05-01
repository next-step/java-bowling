package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;

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
}
