package bowling.model.state;

import bowling.model.Pins;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class States {

    private final List<State> states;

    public States(State state) {
        this.states = new ArrayList<>();
        this.states.add(state);
    }

    private State last() {
        return this.states.get(this.states.size() - 1);
    }

    public boolean isFinish() {
        return last().isFinish();
    }

    public boolean isStrike() {
        return last() instanceof Strike;
    }

    public boolean isSpare() {
        return last() instanceof Spare;
    }

    public void add(State state) {
        this.states.add(state);
    }

    public String getDesc() {
        return this.states.stream()
                    .filter(state -> !(state instanceof Ready))
                    .map(State::getDesc)
                    .collect(Collectors.joining("|"));
    }

    public State bowl(Pins knockedDown) {
        return last().bowl(knockedDown);
    }
}
