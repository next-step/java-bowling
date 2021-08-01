package bowling.domain.state;

import java.util.Stack;

public class LastEnd extends EndState {
    private final Stack<State> states;

    public LastEnd(Stack<State> states) {
        this.states = states;
    }

    public static LastEnd init(Stack<State> states) {
        return new LastEnd(states);
    }
}
