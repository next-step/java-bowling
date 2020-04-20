package bowling.domain.frame.state;

import java.util.Collections;
import java.util.List;

public class States {
    private static final int LAST = 1;

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

    public boolean hasNotBonusState() {
        return stateHistory.stream().noneMatch(Strike.class::isInstance) &&
                stateHistory.stream().noneMatch(Spare.class::isInstance);
    }

    public State getLast() {
        return stateHistory.get(stateHistory.size() - LAST);
    }

    public boolean hasCalculableSize(final int leftCount) {
        return stateHistory.size() >= leftCount;
    }

    public int getSize() {
        return stateHistory.size();
    }
}
