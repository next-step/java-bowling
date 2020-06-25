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

    public int getSize() {
        return this.states.size();
    }

    public State getLastState() {
        if (this.states.size() == 0) {
            return State.READY;
        }
        return this.states.get(this.states.size() - 1);
    }
}
