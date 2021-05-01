package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;

public final class FrameStatusView {

    private static final String DELIMITER = "|";
    private static final String STRIKE_SIGN = "X";
    private static final String SPARE_SIGN = "/";
    private static final String EMPTY_STRING = "";
    private static final int THROW_ONCE = 1;
    private static final int THROW_TWICE = 2;

    private final Frame frame;

    public FrameStatusView(Frame frame) {
        this.frame = frame;
    }

    public String frameStatus() {
        if (frame.isNotStarted()) {
            return EMPTY_STRING;
        }
        if (frame.isFinalFrame()) {
            return finalFrameStatus();
        }
        return normalFrameStatus();
    }

    private String normalFrameStatus() {
        final Pins pins = frame.pins();
        final Pin firstPin = pins.firstPin();
        final Pin secondPin = pins.secondPin();

        if (pins.size() == THROW_ONCE) {
            return pinStatus(firstPin);
        }

        return twoPinStatus(firstPin, secondPin);
    }

    private String pinStatus(Pin pin) {
        if (pin.isMaximum()) {
            return STRIKE_SIGN;
        }
        return pin.status();
    }

    private String twoPinStatus(Pin pin, Pin otherPin) {
        final boolean isSpare = pin.sum(otherPin).isMaximum();
        if (isSpare) {
            return pinStatus(pin) + DELIMITER + SPARE_SIGN;
        }

        return pinStatus(pin) + DELIMITER + otherPin.status();
    }

    private String finalFrameStatus() {
        final Pins pins = frame.pins();
        final Pin firstPin = pins.firstPin();

        if (pins.size() == THROW_ONCE) {
            return pinStatus(firstPin);
        }
        if (pins.size() == THROW_TWICE) {
            return finalTwiceStatus(pins);
        }

        return bonusStatus(pins);
    }

    private String finalTwiceStatus(Pins pins) {
        final Pin firstPin = pins.firstPin();
        final Pin secondPin = pins.secondPin();

        final boolean isSpare = (firstPin.pinCount() + secondPin.pinCount() == Pin.MAX_COUNT);
        if (isSpare) {
            return pinStatus(firstPin) + DELIMITER + SPARE_SIGN;
        }

        return pinStatus(firstPin) + DELIMITER + pinStatus(secondPin);
    }

    private String bonusStatus(Pins pins) {
        final Pin thirdPin = pins.thirdPin();
        final Pin firstPin = pins.firstPin();
        final Pin secondPin = pins.secondPin();

        final boolean isFirstPinStrike = firstPin.isMaximum();
        final boolean isSecondPinStrike = secondPin.isMaximum();

        if (isFirstPinStrike && !isSecondPinStrike) {
            return pinStatus(firstPin) + DELIMITER + twoPinStatus(secondPin, thirdPin);
        }

        return finalTwiceStatus(pins) + DELIMITER + pinStatus(thirdPin);
    }
}
