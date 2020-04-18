package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Score;
import bowling.framestate.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SpareLastFrameOver implements State {

    private List<Integer> pins;

    private SpareLastFrameOver(final List<Integer> pins) {
        validatePins(pins);
        this.pins = new ArrayList<>(pins);
    }

    private void validatePins(final List<Integer> pins) {
        if(Objects.isNull(pins) || pins.size() != 3) {
            throw new IllegalStateException("Last frame spare case must be has three pin scores.");
        }
    }

    public static SpareLastFrameOver newInstance(final List<Integer> pins) {
        return new SpareLastFrameOver(pins);
    }

    @Override
    public State bowl(int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(calculateScore(), LeftScoreCount.of(0));
    }

    @Override
    public FrameScore addingUpFrameScore(FrameScore beforeScore) {
        return beforeScore.addingUp(pins);
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Integer> getPins() {
        return pins;
    }

    private Score calculateScore() {
        return Score.of(pins.get(0) + pins.get(1) + pins.get(2) + pins.get(2));
    }
}
