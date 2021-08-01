package bowling.domain.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LastEnd extends EndState {
    private final Stack<State> states;

    public LastEnd(List<State> states) {
        this.states = new Stack<>();
        this.states.addAll(states);
    }

    public static LastEnd init(List<State> states) {
        return new LastEnd(states);
    }

    @Override
    public List<State> getState() {
        return new ArrayList<>(states);
    }
}
