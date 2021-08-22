package bowling.state;

import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class LastStateProxy implements State {
    public static final int LIMIT_PITCH_COUNT = 3;

    private LastStateDecorator state;

    private int pitchCount;

    public LastStateProxy() {
        state = LastStateDecorator.init();
    }

    public static LastStateProxy init() {
        return new LastStateProxy();
    }

    @Override
    public State nextPitch(final Pin downedPins) {
        increasePitchCount();
        hit(downedPins);
        return updateState();
    }

    private int increasePitchCount() {
        return pitchCount++;
    }

    private void hit(final Pin pin) {
        state.nextPitch(pin);
    }

    private State updateState() {
        if (isEnd()) {
            state.tailStateCheck();
            return End.init();
        }
        return this;
    }

    @Override
    public List<Integer> getScore() {
        return Collections.emptyList();
    }

    @Override
    public boolean isEnd() {
        return state.isEnd() || isTryCountMax();
    }

    private boolean isTryCountMax() {
        return pitchCount == LIMIT_PITCH_COUNT;
    }

    @Override
    public boolean isClean() {
        return false;
    }

    public List<State> getStates() {
        return state.getStates();
    }
}
