package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        if (!(o instanceof Spare)) return false;
        Spare spare = (Spare) o;
        return Objects.equals(firstPinCount, spare.firstPinCount) &&
                Objects.equals(secondPinCount, spare.secondPinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPinCount, secondPinCount);
    }
}
