package bowling.domain;

import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {

    private final List<State> stateHistory;

    public History() {
        this.stateHistory = new ArrayList<>();
    }

    public void add(State state) {
        stateHistory.add(state);
    }

    public List<State> getValue() {
        return Collections.unmodifiableList(stateHistory);
    }
}
