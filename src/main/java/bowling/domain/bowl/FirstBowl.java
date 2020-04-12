package bowling.domain.bowl;

import bowling.domain.frame.score.FrameScore;
import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;

import java.util.Objects;

public class FirstBowl implements Bowl {
    private final Pins pins;
    private final FrameScore frameScore;

    private FirstBowl(final Pins pins, final FrameScore frameScore) {
        this.pins = pins;
        this.frameScore = frameScore;
    }

    public static FirstBowl of() {
        return new FirstBowl(Pins.of(), FrameScore.RUNNING);
    }

    public static FirstBowl valueOf(final Pins pins, final FrameScore frameScore) {
        return new FirstBowl(pins, frameScore);
    }

    Pins getPins() {
        return pins;
    }

    @Override
    public FirstBowl bowl(final BowlCount bowlCount) {
        final Pins remains = pins.knockOver(bowlCount);
        if (remains.isNotExist()) {
            return new FirstBowl(remains, FrameScore.STRIKE);
        }
        return new FirstBowl(remains, FrameScore.RUNNING);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FirstBowl firstBowl = (FirstBowl) o;
        return Objects.equals(pins, firstBowl.pins) &&
                frameScore == firstBowl.frameScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, frameScore);
    }
}
