package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.Stack;

public class LastInProgress extends State {

    private final Stack<State> states;

    public LastInProgress() {
        this.states = new Stack<>();
    }

    public static LastInProgress init() {
        return new LastInProgress();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        return null;
    }
}
