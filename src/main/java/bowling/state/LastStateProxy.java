package bowling.state;

import bowling.pin.Pin;

import java.util.Collections;
import java.util.List;

public class LastStateProxy implements State {
    public static final int LIMIT_PITCH_COUNT = 3;

    private LastStateDecorator lastState;

    private int currentPitchCount;

    public LastStateProxy() {
        lastState = LastStateDecorator.init();
    }

    public static LastStateProxy init() {
        return new LastStateProxy();
    }

    @Override
    public State nextPitch(final Pin pin) {
        currentPitchCount++;
        lastState.nextPitch(pin);
        if (isEnd()) {
            lastState.tailStateCheck();
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
        return lastState.isEnd() || currentPitchCount == LIMIT_PITCH_COUNT;
    }

    @Override
    public boolean isClean() {
        return false;
    }
}
