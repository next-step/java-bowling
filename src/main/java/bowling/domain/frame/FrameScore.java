package bowling.domain.frame;

import bowling.domain.pin.NullPins;
import bowling.domain.pin.Pins;

import java.util.Objects;

public final class FrameScore {

    private final Pins pins;
    private final FrameStatus frameStatus;

    public FrameScore() {
        this(new NullPins());
    }

    public FrameScore(Pins pins) {
        this(pins, pins.frameStatus());
    }

    public FrameScore(Pins pins, FrameStatus frameStatus) {
        this.pins = pins;
        this.frameStatus = frameStatus;
    }

    public boolean isEnded() {
        return FrameStatus.NONE != frameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return Objects.equals(pins, that.pins) && frameStatus == that.frameStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, frameStatus);
    }
}
