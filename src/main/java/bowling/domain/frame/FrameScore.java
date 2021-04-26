package bowling.domain.frame;

import bowling.domain.pin.NullPins;
import bowling.domain.pin.Pins;

import java.util.Objects;

public final class FrameScore {

    public static final String PIN_DELIMITER = "|";
    public static final String SPARE_PIN_STATUS = "/";
    public static final String STRIKE_PIN_STATUS = "X";
    public static final String EMPTY_STRING = "";

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

    public String status() {
        if (frameStatus == FrameStatus.STRIKE) {
            return STRIKE_PIN_STATUS;
        }
        if (frameStatus == FrameStatus.SPARE) {
            return pins.firstPin().status() + PIN_DELIMITER + SPARE_PIN_STATUS;
        }
        if (frameStatus == FrameStatus.NONE) {
            return EMPTY_STRING;
        }
        return pins.firstPin().status() + PIN_DELIMITER + pins.secondPin().status();
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
