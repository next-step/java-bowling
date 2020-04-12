package bowling.domain.bowl;

import bowling.domain.frame.score.FrameScore;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;

import java.util.Objects;

public class SecondBowl implements Bowl {
    private final Pins pins;
    private final FrameScore frameScore;

    public SecondBowl(final Pins pins, final FrameScore frameScore) {
        this.pins = pins;
        this.frameScore = frameScore;
    }

    public static SecondBowl valueOf(final Pins pins) {
        return new SecondBowl(pins, FrameScore.MISS);
    }

    public static SecondBowl valueOf(final Pins pins, final FrameScore frameScore) {
        return new SecondBowl(pins, frameScore);
    }

    @Override
    public SecondBowl bowl(final BowlCount bowlCount) {
        //if (firstBowl.getRemainPins() < bowlCount.count()) {
        final Pins remains = pins.knockOver(bowlCount);
        if (remains.isNotExist()) {
            return new SecondBowl(remains, FrameScore.SPARE);
        }

        if (remains.isGutter()) {
            return new SecondBowl(remains, FrameScore.GUTTER);
        }

        return new SecondBowl(remains, FrameScore.MISS);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SecondBowl that = (SecondBowl) o;
        return Objects.equals(pins, that.pins) &&
                frameScore == that.frameScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, frameScore);
    }
}
