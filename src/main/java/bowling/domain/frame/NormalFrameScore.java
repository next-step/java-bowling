package bowling.domain.frame;

import bowling.domain.pin.NullPins;
import bowling.domain.pin.Pins;

public final class NormalFrameScore extends FrameScore {

    public static final String PIN_DELIMITER = "|";
    public static final String SPARE_PIN_STATUS = "/";
    public static final String STRIKE_PIN_STATUS = "X";
    public static final String EMPTY_STRING = "";

    public NormalFrameScore() {
        this(new NullPins());
    }

    public NormalFrameScore(Pins pins) {
        this(pins, pins.frameStatus());
    }

    public NormalFrameScore(Pins pins, FrameStatus frameStatus) {
        super(pins, frameStatus);
    }

    @Override
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

}
