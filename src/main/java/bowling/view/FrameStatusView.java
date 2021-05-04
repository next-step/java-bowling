package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.pin.BallThrows;
import bowling.domain.pin.Pin;
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

        final BallThrows ballThrows = frame.pins();
        if (frame.isFinalFrame()) {
            return finalFrameStatus(ballThrows);
        }
        return normalFrameStatus(ballThrows);
    }

    private String normalFrameStatus(BallThrows ballThrows) {
        final Pin firstPin = ballThrows.firstPin();

        if (ballThrows.throwCount() == THROW_ONCE) {
            return pinStatus(firstPin);
        }
        final Pin secondPin = ballThrows.secondPin();

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

    private String finalFrameStatus(BallThrows ballThrows) {
        final Pin firstPin = ballThrows.firstPin();

        if (ballThrows.throwCount() == THROW_ONCE) {
            return pinStatus(firstPin);
        }
        if (ballThrows.throwCount() == THROW_TWICE) {
            return finalTwiceStatus(ballThrows);
        }

        return bonusStatus(ballThrows);
    }

    private String finalTwiceStatus(BallThrows ballThrows) {
        final Pin firstPin = ballThrows.firstPin();
        final Pin secondPin = ballThrows.secondPin();

        final boolean isSpare = (firstPin.pinCount() + secondPin.pinCount() == Pin.MAX_COUNT);
        if (isSpare) {
            return pinStatus(firstPin) + DELIMITER + SPARE_SIGN;
        }

        return pinStatus(firstPin) + DELIMITER + pinStatus(secondPin);
    }

    private String bonusStatus(BallThrows ballThrows) {
        final Pin firstPin = ballThrows.firstPin();
        final Pin secondPin = ballThrows.secondPin();
        final Pin thirdPin = ballThrows.thirdPin();

        final boolean isFirstPinStrike = firstPin.isMaximum();
        final boolean isSecondPinStrike = secondPin.isMaximum();

        if (isFirstPinStrike && !isSecondPinStrike) {
            return pinStatus(firstPin) + DELIMITER + twoPinStatus(secondPin, thirdPin);
        }

        return finalTwiceStatus(ballThrows) + DELIMITER + pinStatus(thirdPin);
    }
}
