package bowling.refactor.framestate.common;

import bowling.refactor.framestate.State;

public class Miss implements State {

    private final int firstPin;
    private final int secondPin;

    private Miss(final int firstPin, final int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Miss newInstance(final int firstPin, final int secondPin) {
        return new Miss(firstPin, secondPin);
    }

    @Override
    public State Bowl(final int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }
}
