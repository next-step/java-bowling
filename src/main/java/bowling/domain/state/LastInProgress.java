package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.Stack;

public class LastInProgress extends State {
    private static final int MAX_ROLL_COUNT = 3;

    private int rollCount;
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

    @Override
    public boolean isEnd() {
        return rollCount == MAX_ROLL_COUNT || states.peek().isMiss();
    }
}
