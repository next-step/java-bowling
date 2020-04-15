package bowling.domain.frame.state;

import java.util.Collections;
import java.util.List;

public class States {
    private final List<State> stateHistory;

    public States(final List<State> stateHistory) {
        this.stateHistory = stateHistory;
    }

    public void add(State state) {
        stateHistory.add(state);
    }

    public List<State> getList() {
        return Collections.unmodifiableList(stateHistory);
    }

    public boolean isEmpty() {
        return stateHistory.isEmpty();
    }
}
