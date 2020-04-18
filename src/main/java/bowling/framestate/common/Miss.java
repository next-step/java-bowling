package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Score;
import bowling.framestate.State;

import java.util.Arrays;

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

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(Score.of(firstPin + secondPin), LeftScoreCount.of(0));
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(firstPin, secondPin));
    }
}
