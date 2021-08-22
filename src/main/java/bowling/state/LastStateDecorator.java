package bowling.state;

import bowling.pin.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class LastStateDecorator implements State {
    private final Stack<State> states;

    private LastStateDecorator() {
        this.states = new Stack<>();
        states.add(Ready.init());
    }

    public static LastStateDecorator init() {
        return new LastStateDecorator();
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        final State state = states.pop().nextPitch(downedPins);
        states.push(state);
        nextState(state);
        return this;
    }

    private void nextState(final State lastState) {
        if (lastState.isClean()) {
            states.add(Ready.init());
        }
    }

    @Override
    public boolean isClean() {
        return false;
    }

    @Override
    public List<Integer> getScore() {
        return Collections.emptyList();
    }

    @Override
    public boolean isEnd() {
        return states.peek() instanceof End || !isExtraChance();
    }

    private boolean isExtraChance() {
        return !states.peek().isEnd();
    }

    public void tailStateCheck() {
        if (states.peek() instanceof Ready) {
            states.pop();
        }
    }

    public List<State> getStates() {
        return new ArrayList<>(states);
    }
}
