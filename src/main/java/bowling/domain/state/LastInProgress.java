package bowling.domain.state;

import bowling.domain.pin.DownedPins;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LastInProgress extends State {
    private static final int MAX_ROLL_COUNT = 3;

    private int rollCount;
    private final Stack<State> states;

    public LastInProgress() {
        states = new Stack<>();
        states.add(Preparation.instance());
    }

    public static LastInProgress init() {
        return new LastInProgress();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        ++rollCount;

        updateLastState(downedPins);

        return updateTotalState();
    }

    private void updateLastState(DownedPins downedPins) {
        State lastState = states.pop();
        State updatedState = lastState.downPins(downedPins);

        states.add(updatedState);
    }

    private State updateTotalState() {
        if (isEnd()) {
            return LastEnd.init(states);
        }

        giveExtraChance();
        return this;
    }

    private void giveExtraChance() {
        State lastState = states.peek();

        if (lastState.isClean()) {
            states.add(Preparation.instance());
        }
    }

    @Override
    public boolean isEnd() {
        return rollCount == MAX_ROLL_COUNT || states.peek().isMiss();
    }

    @Override
    public List<State> getState() {
        return new ArrayList<>(states);
    }
}
