package bowling.model.frame.finalization;

import bowling.model.Pins;
import bowling.model.frame.BallState;
import bowling.model.frame.Frame;
import bowling.model.frame.FrameNumber;
import bowling.model.frame.state.NotThrown;
import bowling.utility.Assert;

import java.util.Objects;

public final class FinalFrame implements Frame {

    private static final FrameNumber NUMBER = FrameNumber.LAST;
    private static final int THROWN_COUNT_STEP = 1;

    private final BallState state;

    private FinalFrame(BallState state) {
        Assert.notNull(state, "state must not be null");
        this.state = state;
    }

    public static FinalFrame init() {
        return from(NotThrown.instance());
    }

    public static FinalFrame from(BallState state) {
        return new FinalFrame(state);
    }

    @Override
    public Frame next(Pins countOfHit) {
        validateState();
        if (hasBonusCount()) {
            return from(BonusThrown.of(BonusHit.of(state, countOfHit), state.bonusCount() - THROWN_COUNT_STEP));
        }
        return from(state.state(countOfHit));
    }

    @Override
    public boolean isEnd() {
        return !hasBonusCount() && state.isEnd();
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public FrameNumber number() {
        return NUMBER;
    }

    private void validateState() {
        if (isEnd()) {
            throw new IllegalStateException(String.format("final frame(%s) is already end", this));
        }
    }

    private boolean hasBonusCount() {
        return state.bonusCount() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "state=" + state +
                '}';
    }
}
