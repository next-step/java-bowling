package bowling.framestate.common;

import bowling.FrameScore;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;

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
    public State bowl(final int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createSpare();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(firstPin, secondPin));
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Integer> getPins() {
        return Arrays.asList(firstPin, secondPin);
    }
}
