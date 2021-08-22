package bowling.state;

import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class LastStateProxy implements State {
    public static final int LIMIT_PITCH_COUNT = 3;

    private LastStateDecorator state;

    private int currentPitchCount;

    public LastStateProxy() {
        state = LastStateDecorator.init();
    }

    public static LastStateProxy init() {
        return new LastStateProxy();
    }

    @Override
    public State nextPitch(final Pin pin) {
        currentPitchCount++;
        state.nextPitch(pin);
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
        return state.isEnd() || currentPitchCount == LIMIT_PITCH_COUNT;
    }

    @Override
    public boolean isClean() {
        return false;
    }

    public List<State> getStates() {
        return state.getStates();
    }
}
