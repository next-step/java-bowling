package bowling.domain.state;

import java.util.Collections;
import java.util.List;

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

    public boolean hasBonus() {
        if (states.size() == 0 || states.size() == 1) {
            return false;
        }
        return states.stream().noneMatch(state -> state.isClear());
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
