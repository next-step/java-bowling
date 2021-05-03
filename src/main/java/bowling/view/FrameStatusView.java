package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.pin.Pin;
import bowling.domain.pin.Pins;
import bowling.view.ui.Cell;

public final class FrameStatusView {

    private static final String DELIMITER = "|";
    private static final String STRIKE_SIGN = "X";
    private static final String SPARE_SIGN = "/";
    private static final String GUTTER_SIGN = "-";
    private static final String EMPTY_STRING = "";
    private static final int THROW_ONCE = 1;
    private static final int THROW_TWICE = 2;

    private final Frame frame;

    private FrameStatusView(Frame frame) {
        this.frame = frame;
    }

    public static FrameStatusView from(Frame frame) {
        return new FrameStatusView(frame);
    }

    public Cell cell() {
        return Cell.center(frameStatus());
    }

    public String frameStatus() {
        if (frame.isNotStarted()) {
            return EMPTY_STRING;
        }

        final Pins pins = frame.pins();
        if (frame.isFinalFrame()) {
            return finalFrameStatus(pins);
        }
        return normalFrameStatus(pins);
    }

    private String normalFrameStatus(Pins pins) {
        final Pin firstPin = pins.firstPin();

        if (pins.size() == THROW_ONCE) {
            return pinStatus(firstPin);
        }
        final Pin secondPin = pins.secondPin();

        return twoPinStatus(firstPin, secondPin);
    }

    private String pinStatus(Pin pin) {
        if (pin.isMaximum()) {
            return STRIKE_SIGN;
        }
        if (pin.isGutter()) {
            return GUTTER_SIGN;
        }
        return String.valueOf(pin.pinCount());
    }

    private String twoPinStatus(Pin pin, Pin otherPin) {
        final boolean isSpare = pin.sum(otherPin).isMaximum();
        if (isSpare) {
            return pinStatus(pin) + DELIMITER + SPARE_SIGN;
        }

        return pinStatus(pin) + DELIMITER + pinStatus(otherPin);
    }

    private String finalFrameStatus(Pins pins) {
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
        final Pin firstPin = pins.firstPin();
        final Pin secondPin = pins.secondPin();
        final Pin thirdPin = pins.thirdPin();

        final boolean isFirstPinStrike = firstPin.isMaximum();
        final boolean isSecondPinStrike = secondPin.isMaximum();

        if (isFirstPinStrike && !isSecondPinStrike) {
            return pinStatus(firstPin) + DELIMITER + twoPinStatus(secondPin, thirdPin);
        }

        return finalTwiceStatus(pins) + DELIMITER + pinStatus(thirdPin);
    }
}
