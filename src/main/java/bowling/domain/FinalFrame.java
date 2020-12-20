package bowling.domain;

import bowling.domain.interfaces.Frame;
import bowling.domain.interfaces.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bowling.domain.Condition.FINISH_NOT_PERFECT;
import static bowling.domain.Condition.READY;

public class FinalFrame implements Frame {

    private final LinkedList<State> states;
    private int bowlCount;

    public FinalFrame(LinkedList<State> states, int bowlCount) {
        this.states = states;
        this.bowlCount = bowlCount;
    }

    public FinalFrame() {
        this.states = new LinkedList<>();
        this.states.add(new FirstPitch());
        bowlCount = 0;
    }

    @Override
    public List<Pins> getPinsList() {
        return states.stream()
            .filter(state -> state.getCondition() != READY)
            .map(State::getPins)
            .collect(Collectors.toList());
    }

    public boolean isGameEnd() {
        if (states.isEmpty()) {
            return false;
        }
        if (states.getFirst().getCondition() == FINISH_NOT_PERFECT) {
            return true;
        }
        if (bowlCount >= 3) {
            return true;
        }
        return false;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    public Frame bowl(int count) {
        bowlCount++;
        State currentState = states.getLast();
        states.removeLast();
        currentState = currentState.bowl(count);
        states.add(currentState);

        if (!currentState.getCondition().isFinished()) {
            return this;
        }

        if (!isGameEnd()) {
            states.add(new FirstPitch());
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return bowlCount == that.bowlCount &&
            Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, bowlCount);
    }
}
