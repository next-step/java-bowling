package bowling.framestate.last;

import bowling.FrameScore;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;

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
    public State bowl(final int countOfPin) {
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

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Integer> getPins() {
        return Arrays.asList(firstPin, secondPin);
    }
}
