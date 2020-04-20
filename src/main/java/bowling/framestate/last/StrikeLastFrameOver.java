package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StrikeLastFrameOver implements State {

    private final Pin secondPinCount;

    private StrikeLastFrameOver(final Pin pinCount) {
        this.secondPinCount = pinCount;
    }

    public static StrikeLastFrameOver newInstance(final Pin pinCount) {
        return new StrikeLastFrameOver(pinCount);
    }

    @Override
    public State bowl(final Pin pinCount) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(calculateScore(), LeftScoreCount.of(0));
    }

    @Override
    public FrameScore addNextAddingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Arrays.asList(Score.ofAllPins(), secondPinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(Pin.ofMax(), secondPinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StrikeLastFrameOver)) return false;
        StrikeLastFrameOver that = (StrikeLastFrameOver) o;
        return Objects.equals(secondPinCount, that.secondPinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondPinCount);
    }

    private Score calculateScore() {
        return Score.of(Score.ofAllPins(), secondPinCount.toScore(), secondPinCount.toScore());
    }
}
