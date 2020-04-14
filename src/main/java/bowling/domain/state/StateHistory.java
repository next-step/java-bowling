package bowling.domain.state;

import java.util.ArrayList;
import java.util.List;

public class StateHistory {

    private final List<State> stateHistory;

    public StateHistory() {
        this.stateHistory = new ArrayList<>();
    }

    public void add(State state) {
        stateHistory.add(state);
    }

    public int getSize() {
        return stateHistory.size();
    }

    public State get(int number) {
        return stateHistory.get(number);
    }

    public List<State> getValue() {
        return stateHistory;
    }
}
