package bowling.domain.state;

import java.util.List;

public class States {
    private final List<State> states;

    public States(final List<State> states) {
        this.states = states;
    }

    public void add(State state) {
        states.add(state);
    }

    public boolean hasNotBonus() {
        return true;
    }

}
