package bowling.domain;

import bowling.domain.state.PinCount;
import bowling.domain.state.State;
import bowling.exception.NoMoreBowlActionsException;
import bowling.exception.NoMoreCountingActionException;

import static bowling.util.BowlingFixture.*;

public final class NormalFrame implements Frame {

    private State state;
    private Frame next;
    private final int index;

    public NormalFrame(final int index) {
        this.state = State.ready();
        this.next = Frame.fakeFrame();
        this.index = index;
    }

    public static final Frame initialize() {
        return new NormalFrame(FRAME_START_INDEX);
    }

    public static final Frame from(final int index) {
        return new NormalFrame(index);
    }

    private final void validateFinish() {
        if (state.isFinish()) {
            throw new NoMoreBowlActionsException();
        }
    }

    private final Frame nextFrame() {
        if (index + ONE == FRAME_LAST_INDEX) {
            return FinalFrame.initialize();
        }
        return new NormalFrame(index + ONE);
    }

    @Override
    public final Frame bowl(final PinCount hitCount) {
        validateFinish();
        state = state.bowl(hitCount);
        if (state.isFinish()) {
            next = nextFrame();
            return next;
        }
        return this;
    }

    @Override
    public final boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public final int index() {
        return index;
    }

    @Override
    public final int size() {
        return state.size();
    }

    @Override
    public final int firstCount() {
        return state.firstCount();
    }

    @Override
    public final int secondCount() {
        return state.secondCount();
    }

    @Override
    public final int thirdCount() {
        throw new NoMoreCountingActionException();
    }

}
