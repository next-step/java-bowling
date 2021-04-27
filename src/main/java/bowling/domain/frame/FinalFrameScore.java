package bowling.domain.frame;

import bowling.domain.pin.FinalPins;
import bowling.domain.pin.Pin;

public final class FinalFrameScore extends FrameScore {

    public static final int MAX_PIN_COUNT = 10;

    public FinalFrameScore(FinalPins pins) {
        this(pins, pins.frameStatus());
    }

    private FinalFrameScore(FinalPins pins, FrameStatus frameStatus) {
        super(pins, frameStatus);
    }

    @Override
    public String status() {
        if (frameStatus == FrameStatus.NOT_ENDED) {
            return EMPTY_STRING;
        }
        final FinalPins finalPins = (FinalPins) pins;
        final Pin firstPin = finalPins.firstPin();
        final Pin secondPin = finalPins.secondPin();
        final Pin thirdPin = finalPins.thirdPin();

        if (firstPin.pinCount() == MAX_PIN_COUNT) {
            final boolean isSecondPinStrike = secondPin.pinCount() == MAX_PIN_COUNT;
            return STRIKE_PIN_STATUS + PIN_DELIMITER + (isSecondPinStrike ? STRIKE_PIN_STATUS : secondPin.status()) + PIN_DELIMITER + (isSecondPinStrike ? thirdPin.pinCount() == MAX_PIN_COUNT ? STRIKE_PIN_STATUS : thirdPin.status() : secondPin.sum(thirdPin).pinCount() == MAX_PIN_COUNT ? SPARE_PIN_STATUS : thirdPin.status());
        }
        if (firstPin.pinCount() + secondPin.pinCount() == MAX_PIN_COUNT) {
            return firstPin.status() + PIN_DELIMITER + SPARE_PIN_STATUS + PIN_DELIMITER + (thirdPin.pinCount() == MAX_PIN_COUNT ? STRIKE_PIN_STATUS : thirdPin.status());
        }
        return firstPin.status() + PIN_DELIMITER + secondPin.status();
    }

    @Override
    public FrameScore knockDownPin(Pin pin) {
        return new FinalFrameScore((FinalPins) pins.knockDownPin(pin));
    }
}
