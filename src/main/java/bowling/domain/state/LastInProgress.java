package bowling.domain.state;

import java.util.Stack;

public class LastInProgress {

    private final Stack<State> states;

    public LastInProgress() {
        this.states = new Stack<>();
    }

    public static LastInProgress init() {
        return new LastInProgress();
    }
}
