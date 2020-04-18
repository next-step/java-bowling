package bowling.refactor.framestate.common;

import bowling.refactor.FrameScore;
import bowling.refactor.framestate.State;

public class Spare implements State {

    private final int firstPin;
    private final int secondPin;

    private Spare(final int firstPin, final int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Spare newInstance(final int firstPin, final int secondPin) {
        return new Spare(firstPin, secondPin);
    }

    @Override
    public State Bowl(final int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createSpare();
    }
}
