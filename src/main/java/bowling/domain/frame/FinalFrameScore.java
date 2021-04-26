package bowling.domain.frame;

import bowling.domain.pin.FinalPins;
import bowling.domain.pin.Pin;

public final class FinalFrameScore extends FrameScore {

    public FinalFrameScore(FinalPins pins) {
        this(pins, pins.frameStatus());
    }

    private FinalFrameScore(FinalPins pins, FrameStatus frameStatus) {
        super(pins, frameStatus);
    }

    @Override
    public String status() {
        if (frameStatus == FrameStatus.NONE) {
            return EMPTY_STRING;
        }
        final FinalPins finalPins = (FinalPins) pins;
        final Pin secondPin = finalPins.secondPin();
        final Pin thirdPin = finalPins.thirdPin();
        if (frameStatus == FrameStatus.STRIKE) {
            final boolean isSecondPinStrike = secondPin.pinCount() == 10;
            return STRIKE_PIN_STATUS + PIN_DELIMITER + (isSecondPinStrike ? STRIKE_PIN_STATUS : secondPin.status()) + PIN_DELIMITER + (isSecondPinStrike ? thirdPin.pinCount() == 10 ? STRIKE_PIN_STATUS : thirdPin.status() : secondPin.sum(thirdPin).pinCount() == 10 ? SPARE_PIN_STATUS : thirdPin.status());
        }
        final Pin firstPin = finalPins.firstPin();
        if (frameStatus == FrameStatus.SPARE) {
            return firstPin.status() + PIN_DELIMITER + SPARE_PIN_STATUS + PIN_DELIMITER + (thirdPin.pinCount() == 10 ? STRIKE_PIN_STATUS : thirdPin.status());
        }
        return firstPin.status() + PIN_DELIMITER + secondPin.status();
    }
}
