package bowling.framestate.last;

import bowling.FrameScore;
import bowling.Pin;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpareLastFrame implements State {

    private final Pin firstPinCount;
    private final Pin secondPinCount;

    private SpareLastFrame(final Pin firstPinCount, final Pin secondPinCount) {
        this.firstPinCount = firstPinCount;
        this.secondPinCount = secondPinCount;
    }

    public static SpareLastFrame newInstance(final Pin firstPinCount, final Pin secondPinCount) {
        return new SpareLastFrame(firstPinCount, secondPinCount);
    }

    @Override
    public State bowl(final Pin pinCount) {
        return SpareLastFrameOver.newInstance(Arrays.asList(firstPinCount, secondPinCount, pinCount));
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.createSpare();
    }

    @Override
    public FrameScore addNextAddingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Arrays.asList(firstPinCount.toScore(), secondPinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(firstPinCount, secondPinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpareLastFrame)) return false;
        SpareLastFrame that = (SpareLastFrame) o;
        return Objects.equals(firstPinCount, that.firstPinCount) &&
                Objects.equals(secondPinCount, that.secondPinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPinCount, secondPinCount);
    }
}
