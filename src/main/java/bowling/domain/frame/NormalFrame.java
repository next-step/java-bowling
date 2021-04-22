package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.attempt.AttemptNumber;
import bowling.domain.attempt.NormalAttemptNumber;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class NormalFrame implements Frame {
    private State state;
    private AttemptNumber attemptNumber;

    private NormalFrame(AttemptNumber normalAttemptNumber) {
        this.attemptNumber = normalAttemptNumber;
    }

    public static Frame init() {
        return new NormalFrame(NormalAttemptNumber.first());
    }

    public static Frame of(int tryCount) {
        return new NormalFrame(NormalAttemptNumber.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {
        this.state = this.getState(pinCount);
        this.attemptNumber = NormalAttemptNumber.of(attemptNumber.increase());
    }

    private State getState(int pinCount) {
        if (attemptNumber.isFirstAttempt()) {
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
        if (state instanceof Strike) {
            return true;
        }
        return attemptNumber.isLastAttempt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NormalFrame that = (NormalFrame)o;
        return Objects.equals(state, that.state) && Objects.equals(attemptNumber, that.attemptNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, attemptNumber);
    }
}
