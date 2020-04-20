package bowling.framestate;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static bowling.Pin.MAX_PIN_COUNT;
import static bowling.Pin.MIN_PIN_COUNT;

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
        Pin sumCount = pinCount.add(this.pinCount);

        if (sumCount.isEqualTo(MAX_PIN_COUNT)) {
            return Spare.newInstance(this.pinCount, pinCount);
        }

        if (sumCount.isEqualTo(MIN_PIN_COUNT)) {
            return Gutter.newInstance();
        }

        return Miss.newInstance(this.pinCount, pinCount);
    }

    @Override
    public FrameScore createFrameScore() {
        return FrameScore.newInstance(pinCount.toScore(), LeftScoreCount.of(1));
    }

    @Override
    public FrameScore sumBeforeScore(final FrameScore beforeScore) {
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
        if (!(o instanceof FirstBowl)) return false;
        FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(pinCount, firstBowl.pinCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount);
    }
}
