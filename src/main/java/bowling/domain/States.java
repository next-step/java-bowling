package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class States {
    private final List<State> states;

    public States() {
        this.states = new ArrayList<>();
    }

    public void add(State state) {
        this.states.add(state);
    }

    public List<State> getStates() {
        return states;
    }
}
