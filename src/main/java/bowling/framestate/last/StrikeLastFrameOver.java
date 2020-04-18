package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Score;
import bowling.framestate.State;

import java.util.Arrays;

public class StrikeLastFrameOver implements State {

    private int secondPin;

    private StrikeLastFrameOver(final int countOfPin) {
        this.secondPin = countOfPin;
    }

    public static State newInstance(final int countOfPin) {
        return new StrikeLastFrameOver(countOfPin);
    }

    @Override
    public State Bowl(int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(calculateScore(), LeftScoreCount.of(0));
    }

    @Override
    public FrameScore addingUpFrameScore(FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(10, secondPin));
    }

    private Score calculateScore() {
        return Score.of(10 + secondPin + secondPin);
    }
}
