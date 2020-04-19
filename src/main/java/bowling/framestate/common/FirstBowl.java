package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstBowl implements State {

    private final Pin pinCount;

    private FirstBowl(final Pin pinCount) {
        this.pinCount = pinCount;
    }

    public static FirstBowl newInstance(final Pin countOfPin) {
        return new FirstBowl(countOfPin);
    }

    @Override
    public State bowl(final Pin pinCount) {
        Pin sumCount = pinCount.sum(this.pinCount);

        if (sumCount.isEqualTo(10)) {
            return Spare.newInstance(this.pinCount, pinCount);
        }

        return Miss.newInstance(this.pinCount, pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(pinCount.toScore(), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(pinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.singletonList(pinCount);
    }
}
