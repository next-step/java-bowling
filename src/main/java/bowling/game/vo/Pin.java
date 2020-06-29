package bowling.game.vo;

import java.util.Objects;

public class Pin {
    public static final int PIN_COUNT_MAX = 10;
    public static final int PIN_COUNT_MIN = 0;

    private final int pinCount;

    public Pin(final int pinCount) {
        validatePinCount(pinCount);
        this.pinCount = pinCount;
    }

    private void validatePinCount(final int pinCount) {
        if (pinCount < PIN_COUNT_MIN || pinCount > PIN_COUNT_MAX) {
            throw new IllegalArgumentException("쓰러진 핀의 갯수는 0 ~ 10 사이어야 합니다. - " + pinCount);
        }
    }

    public static Pin add(Pin pin, Pin before) {
        return new Pin(pin.pinCount + before.pinCount);
    }

    public boolean isMaxCount() {
        return this.pinCount == PIN_COUNT_MAX;
    }

    public boolean isMinCount() {
        return this.pinCount == PIN_COUNT_MIN;
    }

    public int getPinCount() {
        return pinCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;
        Pin pin = (Pin) o;
        return getPinCount() == pin.getPinCount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPinCount());
    }
}
