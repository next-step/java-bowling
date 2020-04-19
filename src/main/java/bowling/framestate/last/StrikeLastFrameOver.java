package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.State;

import java.util.Arrays;
import java.util.List;

public class StrikeLastFrameOver implements State {

    private final Pin secondPinCount;

    private StrikeLastFrameOver(final Pin pinCount) {
        this.secondPinCount = pinCount;
    }

    public static State newInstance(final Pin pinCount) {
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
    public FrameScore addingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addingUp(Arrays.asList(Score.ofAllPins(), secondPinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return Arrays.asList(Pin.ofMax(), secondPinCount);
    }

    private Score calculateScore() {
        return Score.of(Score.ofAllPins(), secondPinCount.toScore(), secondPinCount.toScore());
    }
}
