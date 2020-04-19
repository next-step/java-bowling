package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;

public class Miss implements State {

    private final Pin firstPinCount;
    private final Pin secondPinCount;

    private Miss(final Pin firstPinCount, final Pin secondPinCount) {
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    public static Miss newInstance(final Pin firstPinCount, final Pin secondPinCount) {
        return new Miss(firstPinCount, secondPinCount);
    }

    @Override
    public State bowl(final Pin pinCount) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        Pin sum = firstPinCount.sum(secondPinCount);
        return FrameScore.newInstance(sum.toScore(), LeftScoreCount.of(0));
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
