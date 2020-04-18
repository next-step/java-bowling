package bowling.refactor.framestate.last;

import bowling.refactor.FrameScore;
import bowling.refactor.framestate.State;

import java.util.Arrays;

public class SpareLastFrame implements State {

    private final int firstPin;
    private final int secondPin;

    private SpareLastFrame(final int firstPin, final int secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static SpareLastFrame newInstance(final int firstPin, final int secondPin) {
        return new SpareLastFrame(firstPin, secondPin);
    }

    @Override
    public State Bowl(final int countOfPin) {
        return SpareLastFrameOver.newInstance(Arrays.asList(firstPin, secondPin, countOfPin));
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createSpare();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(firstPin, secondPin));
    }
}
