package bowling.state;

import bowling.pin.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bowling.state.LastStateProxy.LIMIT_PITCH_COUNT;

public class LastStateDecorator implements State {
    private final List<State> states;

    private LastStateDecorator() {
        this.states = new ArrayList<>(LIMIT_PITCH_COUNT);
        states.add(Ready.init());
    }

    public static LastStateDecorator init() {
        return new LastStateDecorator();
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        final State state = popLastState().nextPitch(downedPins);
        states.add(state);
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
        return lastState() instanceof End || !isExtraChance();
    }

    private boolean isExtraChance() {
        return !lastState().isEnd();
    }

    public void tailStateCheck() {
        if (lastState() instanceof Ready) {
            popLastState();
        }
    }

    private State popLastState() {
        return states.remove(states.size() - 1);
    }

    public State lastState() {
        return states.get(states.size() - 1);
    }

    public List<State> getStates() {
        return new ArrayList<>(states);
    }
}
