package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.Objects;

public abstract class FrameScore {

    protected final Pins pins;
    protected final FrameStatus frameStatus;

    protected FrameScore(Pins pins, FrameStatus frameStatus) {
        this.pins = pins;
        this.frameStatus = frameStatus;
    }

    public abstract boolean isEnded();

    public abstract String status();

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
