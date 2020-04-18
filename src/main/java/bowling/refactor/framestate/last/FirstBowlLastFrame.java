package bowling.refactor.framestate.last;

import bowling.refactor.FrameScore;
import bowling.refactor.LeftScoreCount;
import bowling.refactor.Score;
import bowling.refactor.framestate.State;
import bowling.refactor.framestate.common.Miss;

import java.util.Arrays;

public class FirstBowlLastFrame implements State {
    private final int countOfPin;

    private FirstBowlLastFrame(final int countOfPin) {
        this.countOfPin = countOfPin;
    }

    public static FirstBowlLastFrame newInstance(final int countOfPin) {
        return new FirstBowlLastFrame(countOfPin);
    }

    @Override
    public State Bowl(final int countOfPin) {
        if (this.countOfPin + countOfPin == 10) {
            return SpareLastFrame.newInstance(this.countOfPin, countOfPin);
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
}
