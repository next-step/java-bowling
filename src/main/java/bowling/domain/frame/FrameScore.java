package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

import java.util.Objects;

public abstract class FrameScore {

    public static final String PIN_DELIMITER = "|";
    public static final String SPARE_PIN_STATUS = "/";
    public static final String STRIKE_PIN_STATUS = "X";
    public static final String EMPTY_STRING = "";

    protected final Pins pins;

    protected FrameScore(Pins pins) {
        this.pins = pins;
    }

    public abstract String status();

    public abstract void knockDownPin(Pin pin);

    public boolean isEnded() {
        return FrameStatus.NOT_ENDED != pins.frameStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameScore that = (FrameScore) o;
        return Objects.equals(pins, that.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
