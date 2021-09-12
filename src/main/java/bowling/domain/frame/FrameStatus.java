package bowling.domain.frame;

import bowling.domain.pin.Pins;

public enum FrameStatus {

    STRIKE, SPARE, NORMAL;

    public static FrameStatus of(final Pins pins) {
        if (pins.isStrike()) {
            return STRIKE;
        }
        if (pins.isSpare()) {
            return SPARE;
        }
        return NORMAL;
    }
}
