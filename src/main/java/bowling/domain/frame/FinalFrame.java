package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int BONUS_INCLUDED_TOTAL_TRIES = 3;

    private final List<State> states;

    FinalFrame(List<State> states) {
        this.states = states;
    }

    public static FinalFrame init() {
        return new FinalFrame(List.of(new Ready()));
    }

    @Override
    public Frame bowl(FallenPin fallenPin) {
        if (lastState().isFinished()) {
            return addState(fallenPin);
        }

        return updateState(fallenPin);
    }

    @Override
    public boolean isFinished() {
        if (firstState() instanceof Strike || firstState() instanceof Spare) {
            return totalTries() == BONUS_INCLUDED_TOTAL_TRIES;
        }

        return firstState().isFinished();
    }

    @Override
    public List<State> getStates() {
        return states;
    }

    private int totalTries() {
        return states.stream()
                .mapToInt(State::tries)
                .sum();
    }

    private FinalFrame addState(FallenPin fallenPin) {
        List<State> result = new ArrayList<>(states);
        result.add(new Ready().bowl(fallenPin));
        return new FinalFrame(result);
    }

    private FinalFrame updateState(FallenPin fallenPin) {
        List<State> result = new ArrayList<>(states);
        result.set(lastIndex(), lastState().bowl(fallenPin));
        return new FinalFrame(result);
    }

    private State firstState() {
        return states.get(0);
    }

    private State lastState() {
        return states.get(lastIndex());
    }

    private int lastIndex() {
        return states.size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;

        FinalFrame that = (FinalFrame) o;

        return Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return states != null ? states.hashCode() : 0;
    }
}
