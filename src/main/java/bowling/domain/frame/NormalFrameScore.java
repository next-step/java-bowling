package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public final class NormalFrameScore extends FrameScore {

    public NormalFrameScore() {
        this(Pins.create());
    }

    public NormalFrameScore(Pins pins) {
        super(pins);
    }

    @Override
    public String status() {
        final FrameStatus frameStatus = pins.frameStatus();
        if (frameStatus == FrameStatus.STRIKE) {
            return STRIKE_PIN_STATUS;
        }
        if (frameStatus == FrameStatus.SPARE) {
            return pins.firstPin().status() + PIN_DELIMITER + SPARE_PIN_STATUS;
        }
        if (frameStatus == FrameStatus.NOT_ENDED) {
            return EMPTY_STRING;
        }
        return pins.firstPin().status() + PIN_DELIMITER + pins.secondPin().status();
    }

    @Override
    public void knockDownPin(Pin pin) {
        pins.knockDownPin(pin);
    }
}
