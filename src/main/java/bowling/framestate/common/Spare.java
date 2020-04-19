package bowling.framestate.common;

import bowling.FrameScore;
import bowling.Pin;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;

public class Spare implements State {

    private final Pin firstPinCount;
    private final Pin secondPinCount;

    private Spare(final Pin firstPinCount, final Pin secondPinCount) {
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    public static Spare newInstance(final Pin firstPin, final Pin secondPin) {
        return new Spare(firstPin, secondPin);
    }

    @Override
    public State bowl(final Pin countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createSpare();
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(firstPinCount.toScore(), secondPinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(firstPinCount, secondPinCount);
    }
}
