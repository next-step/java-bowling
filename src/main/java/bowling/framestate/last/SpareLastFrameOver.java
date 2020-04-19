package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static bowling.frame.LastBowlingFrame.LAST_FRAME_MAX_BOWL_COUNT;

public class SpareLastFrameOver implements State {

    private List<Pin> pins;

    private SpareLastFrameOver(final List<Pin> pins) {
        validatePins(pins);
        this.pins = new ArrayList<>(pins);
    }

    private void validatePins(final List<Pin> pins) {
        if (Objects.isNull(pins) || pins.size() != LAST_FRAME_MAX_BOWL_COUNT) {
            throw new IllegalStateException("Last frame spare case must be has three pin scores.");
        }
    }

    public static SpareLastFrameOver newInstance(final List<Pin> pins) {
        return new SpareLastFrameOver(pins);
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
    public FrameScore addingUpFrameScore(FrameScore beforeScore) {
        List<Score> scores = pins.stream()
                .map(Pin::toScore)
                .collect(Collectors.toList());

        return beforeScore.addingUp(scores);
    }

    @Override
    public boolean isOver() {
        return true;
    }

    @Override
    public List<Pin> getPins() {
        return pins;
    }

    private Score calculateScore() {
        List<Score> scores = pins.stream()
                .map(Pin::toScore)
                .collect(Collectors.toList());

        return Score.of(scores.get(0), scores.get(1), scores.get(2), scores.get(2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpareLastFrameOver)) return false;
        SpareLastFrameOver that = (SpareLastFrameOver) o;
        return Objects.equals(getPins(), that.getPins());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPins());
    }
}
