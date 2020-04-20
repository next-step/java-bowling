package bowling.framestate;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        Pin sum = firstPinCount.add(secondPinCount);
        return FrameScore.newInstance(sum.toScore(), LeftScoreCount.of(0));
    }

    @Override
    public FrameScore sumBeforeScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(firstPinCount.toScore(), secondPinCount.toScore());
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(firstPinCount, secondPinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Miss)) return false;
        Miss miss = (Miss) o;
        return Objects.equals(firstPinCount, miss.firstPinCount) &&
                Objects.equals(secondPinCount, miss.secondPinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPinCount, secondPinCount);
    }
}
