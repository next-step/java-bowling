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
        if (pins.size() == THROW_ONCE) {
            return onceStatus(pins);
        }

        return normalTwiceStatus(pins);
    }

    private String onceStatus(Pins pins) {
        final Pin firstPin = pins.firstPin();
        if (firstPin.isMaximum()) {
            return STRIKE_SIGN;
        }
        return firstPin.status();
    }

    private String normalTwiceStatus(Pins pins) {
        final Pin firstPin = pins.firstPin();
        final Pin secondPin = pins.secondPin();

        final boolean isSpare = firstPin.sum(secondPin).isMaximum();
        if (isSpare) {
            return onceStatus(pins) + DELIMITER + SPARE_SIGN;
        }

        return onceStatus(pins) + DELIMITER + secondPin.status();
    }

    private String finalFrameStatus() {
        final Pins pins = frame.pins();
        if (pins.size() == THROW_ONCE) {
            return onceStatus(pins);
        }
        if (pins.size() == THROW_TWICE) {
            return finalTwiceStatus(pins);
        }

        return bonusStatus(pins);
    }

    private String finalTwiceStatus(Pins pins) {
        return null;
    }

    private String bonusStatus(Pins pins) {
        return null;
    }
}
