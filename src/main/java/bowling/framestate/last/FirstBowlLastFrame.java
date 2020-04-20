package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.framestate.State;
import bowling.framestate.common.Miss;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static bowling.Pin.MAX_PIN_COUNT;

public class FirstBowlLastFrame implements State {

    private final Pin pinCount;

    private FirstBowlLastFrame(final Pin pinCount) {
        this.pinCount = pinCount;
    }

    public static FirstBowlLastFrame newInstance(final Pin countOfPin) {
        return new FirstBowlLastFrame(countOfPin);
    }

    @Override
    public State bowl(final Pin pinCount) {
        Pin sumCount = pinCount.add(this.pinCount);

        if (sumCount.isEqualTo(MAX_PIN_COUNT)) {
            return SpareLastFrame.newInstance(this.pinCount, pinCount);
        }

        return Miss.newInstance(this.pinCount, pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(pinCount.toScore(), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore addNextAddingUpFrameScore(final FrameScore beforeScore) {
        return beforeScore.addNextAddingUpScores(Collections.singletonList(pinCount.toScore()));
    }

    @Override
    public boolean isOver() {
        return false;
    }

    @Override
    public List<Pin> getPins() {
        return Collections.singletonList(pinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FirstBowlLastFrame)) return false;
        FirstBowlLastFrame that = (FirstBowlLastFrame) o;
        return Objects.equals(pinCount, that.pinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount);
    }
}
