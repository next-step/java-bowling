package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bowling.domain.attempt.AttemptNumber;
import bowling.domain.attempt.FinalAttemptNumber;
import bowling.domain.state.BowlingPin;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

public class FinalFrame implements Frame {
    private final List<State> states;
    private AttemptNumber attemptNumber;

    private FinalFrame(AttemptNumber attemptNumber) {
        this.attemptNumber = attemptNumber;
        this.states = new ArrayList<>();
    }

    public static Frame init() {
        return new FinalFrame(FinalAttemptNumber.first());
    }

    public static Frame of(int tryCount) {
        return new FinalFrame(FinalAttemptNumber.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {
        State state = this.getState(pinCount);
        this.attemptNumber = FinalAttemptNumber.of(this.attemptNumber.increase());
        this.remove(state);
        this.states.add(state);
    }

    private void remove(State state) {
        if (isDone() && (state instanceof Spare || !bonusFrame())) {
            states.remove(0);
        }
    }

    private State getState(int pinCount) {
        if (attemptNumber.isFirstAttempt() || bonusFrame()) {
            return State.newState(BowlingPin.of(pinCount));
        }
        return State.newState(states.get(states.size() - 1).firstHit(), BowlingPin.of(pinCount));
    }

    private boolean bonusFrame() {
        return states.stream().anyMatch(state -> state instanceof Spare || state instanceof Strike);
    }

    @Override
    public Frame next() {
        throw new IllegalStateException("종료 되었습니다.");
    }

    @Override
    public boolean isDone() {
        if (bonusFrame()) {
            return attemptNumber.isBonusAttempt();
        }
        return attemptNumber.isLastAttempt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FinalFrame that = (FinalFrame)o;
        return Objects.equals(states, that.states) && Objects.equals(attemptNumber, that.attemptNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, attemptNumber);
    }
}
