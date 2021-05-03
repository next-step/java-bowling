package bowling.domain.state;

import java.util.Collections;
import java.util.List;

import bowling.domain.state.result.Spare;
import bowling.domain.state.result.Strike;

public class States {
    private final List<State> states;

    public States(final List<State> states) {
        this.states = states;
    }

    public void add(State state) {
        states.add(state);
    }

    public List<State> toList() {
        return Collections.unmodifiableList(states);
    }

    public boolean hasNotBonus() {
        return states.stream().noneMatch(Strike.class::isInstance)
            && states.stream().noneMatch(Spare.class::isInstance);
    }

    public State lastState() {
        return states.get(states.size() - 1);
    }

    public int size() {
        return states.size();
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}
