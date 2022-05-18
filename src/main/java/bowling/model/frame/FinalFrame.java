package bowling.model.frame;

import bowling.model.Pins;
import bowling.utility.Assert;

import java.util.Objects;

public final class FinalFrame implements Frame {

    private static final FrameNumber NUMBER = FrameNumber.LAST;

    private final FrameState state;

    private FinalFrame(FrameState state) {
        Assert.notNull(state, "state must not be null");
        this.state = state;
    }

    static FinalFrame init() {
        return from(FrameState.init());
    }

    static FinalFrame from(FrameState state) {
        return new FinalFrame(state);
    }

    @Override
    public FrameNumber number() {
        return NUMBER;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public boolean isEnd() {
        return state.isEndState() && !hasRemainCount();
    }

    @Override
    public Frame next(Pins countOfHit) {
        if (state.isEndState()) {
            return from(state.addBonusPins(countOfHit));
        }
        return from(state.nextState(countOfHit));
    }

    @Override
    public boolean hasRemainCount() {
        return state.hasRemainCount();
    }

    @Override
    public Frame addBonusPins(Pins countOfHit) {
        return from(state.addBonusPins(countOfHit));
    }

    @Override
    public int sumPinsCount() {
        return state.sumPinsCount();
    }

    @Override
    public String mark() {
        return state.markWithBonus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
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
    public String toString() {
        return "FinalFrame{" +
                "state=" + state +
                '}';
    }
}
