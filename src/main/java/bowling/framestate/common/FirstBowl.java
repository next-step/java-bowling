package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Score;
import bowling.framestate.State;

import java.util.Arrays;

public class FirstBowl implements State {

    private final int countOfPin;

    private FirstBowl(final int countOfPin) {
        this.countOfPin = countOfPin;
    }

    public static FirstBowl newInstance(final int countOfPin) {
        return new FirstBowl(countOfPin);
    }

    @Override
    public State bowl(final int countOfPin) {
        if (this.countOfPin + countOfPin == 10) {
            return Spare.newInstance(this.countOfPin, countOfPin);
        }

        return Miss.newInstance(this.countOfPin, countOfPin);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(Score.of(countOfPin), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(countOfPin, 0));
    }

    @Override
    public boolean isOver() {
        return false;
    }

}
