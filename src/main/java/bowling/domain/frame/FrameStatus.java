package bowling.domain.frame;

import bowling.domain.pin.Pin;

public enum FrameStatus {
    STRIKE,
    SPARE,
    MISS,
    NORMAL,
    NOT_ENDED;

    private static final int MAX_PIN_COUNT = 10;
    private static final int ZERO_PIN_COUNT = 0;

    public static FrameStatus of(Pin firstPin, Pin secondPin) {
        if (firstPin != null && firstPin.pinCount() == MAX_PIN_COUNT) {
            return STRIKE;
        }
        if (firstPin == null || secondPin == null) {
            return NOT_ENDED;
        }
        if (firstPin.pinCount() + secondPin.pinCount() == MAX_PIN_COUNT) {
            return SPARE;
        }
        if (firstPin.pinCount() + secondPin.pinCount() == ZERO_PIN_COUNT) {
            return MISS;
        }
        return NORMAL;
    }

    // TODO: 마지막 프레임은 상태를 가지지 않도록 변경
    @Deprecated
    public static FrameStatus of(Pin firstPin, Pin secondPin, Pin thirdPin) {
        if (firstPin == null || secondPin == null) {
            return NOT_ENDED;
        }
        if (firstPin.pinCount() == MAX_PIN_COUNT && secondPin.pinCount() == MAX_PIN_COUNT && thirdPin == null) {
            return NOT_ENDED;
        }
        if (firstPin.pinCount() + secondPin.pinCount() == MAX_PIN_COUNT && thirdPin == null) {
            return NOT_ENDED;
        }
        return NORMAL;
    }
}
