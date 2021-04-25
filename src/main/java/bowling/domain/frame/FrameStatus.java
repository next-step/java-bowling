package bowling.domain.frame;

import bowling.domain.pin.Pin;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS,
    GUTTER,
    NONE;

    private static final int MAX_PIN_COUNT = 10;
    private static final int ZERO_PIN_COUNT = 0;

    public static FrameStatus of(Pin firstPin, Pin secondPin) {
        if (firstPin.pinCount() == MAX_PIN_COUNT) {
            return STRIKE;
        }
        if (firstPin.pinCount() + secondPin.pinCount() == MAX_PIN_COUNT) {
            return SPARE;
        }
        if (firstPin.pinCount() + secondPin.pinCount() == ZERO_PIN_COUNT) {
            return GUTTER;
        }
        return MISS;
    }
}
