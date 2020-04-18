package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Score;
import bowling.framestate.State;
import bowling.framestate.common.Miss;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstBowlLastFrame implements State {

    private final int countOfPin;

    private FirstBowlLastFrame(final int countOfPin) {
        this.countOfPin = countOfPin;
    }

    public static FirstBowlLastFrame newInstance(final int countOfPin) {
        return new FirstBowlLastFrame(countOfPin);
    }

    @Override
    public State bowl(final int countOfPin) {
        if (this.countOfPin + countOfPin > 10) {
            throw new IllegalStateException("The frame maximum pin count is 10");
        }

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

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Integer> getPins() {
        return Collections.singletonList(countOfPin);
    }
}
