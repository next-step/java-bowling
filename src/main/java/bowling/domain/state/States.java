package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class States {

    private final List<State> states;

    public States(State state) {
        this.states = new ArrayList<>();
        this.states.add(state);
    }

    public States(List<State> states) {
        this.states = states;
    }

    private State end() {
        return this.states.get(this.states.size() - 1);
    }

    public boolean isFinished() {
        return end().isFinished();
    }

    public boolean isStrike() {
        return end() instanceof Strike;
    }

    public boolean isSpare() {
        return end() instanceof Spare;
    }

    public void add(State state) {
        this.states.add(state);
    }

    public String getMark() {
        return this.states.stream()
                .filter(state -> !(state instanceof Ready))
                .map(State::getMark)
                .collect(Collectors.joining("|"));
    }

    public State bowl(Pins knockedDown) {
        return end().bowl(knockedDown);
    }
}
