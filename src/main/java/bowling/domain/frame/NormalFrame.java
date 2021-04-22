package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.state.BowlingPin;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private State state;
    private TryCount tryCount;

    private NormalFrame(TryCount tryCount) {
        this.tryCount = tryCount;
    }

    public static Frame init() {
        return new NormalFrame(TryCount.first());
    }

    public static Frame of(int tryCount) {
        return new NormalFrame(TryCount.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {
        this.state = this.getState(pinCount);
        this.tryCount = TryCount.of(this.tryCount.toInteger() + 1);
    }

    private State getState(int pinCount) {
        if (tryCount.isFirstHit()) {
            return State.newState(BowlingPin.of(pinCount));
        }
        return State.newState(state.firstHit(), BowlingPin.of(pinCount));
    }

    @Override
    public Frame next() {
        return NormalFrame.init();
    }

    @Override
    public boolean isDone() {
        return tryCount.isMaxHit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NormalFrame that = (NormalFrame)o;
        return Objects.equals(state, that.state) && Objects.equals(tryCount, that.tryCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, tryCount);
    }
}
