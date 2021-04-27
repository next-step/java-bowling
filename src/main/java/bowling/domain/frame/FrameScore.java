package bowling.domain.frame;

import bowling.domain.pin.Pins;

import java.util.Objects;

public abstract class FrameScore {

    public static final String PIN_DELIMITER = "|";
    public static final String SPARE_PIN_STATUS = "/";
    public static final String STRIKE_PIN_STATUS = "X";
    public static final String EMPTY_STRING = "";

    protected final Pins pins;
    protected final FrameStatus frameStatus;

    protected FrameScore(Pins pins, FrameStatus frameStatus) {
        this.pins = pins;
        this.frameStatus = frameStatus;
    }

    public abstract String status();

    public boolean isEnded() {
        return FrameStatus.NOT_ENDED != frameStatus;
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
