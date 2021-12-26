package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MIN_PIN_COUNT = 0;
    public static final int MAX_PIN_COUNT = 10;

    private final int fallenPinCount;

    public Pin(int fallenPinCount) {
        validateRange(fallenPinCount);
        this.fallenPinCount = fallenPinCount;
    }

    private void validateRange(int value) {
        if (value < MIN_PIN_COUNT || value > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("쓰러트린 볼링핀 수는 0~10 사이 정수입니다.");
        }
    }

    public static Pin first() {
        return new Pin(0);
    }

    public static Pin of(int fallenPinCount) {
        return new Pin(fallenPinCount);
    }

    public int getFallenPinCount() {
        return fallenPinCount;
    }

    public boolean isStrike() {
        return fallenPinCount == MAX_PIN_COUNT;
    }

    public boolean isSpare(Pin other) {
        return fallenPinCount + other.fallenPinCount == MAX_PIN_COUNT;
    }

    public boolean isGutter() {
        return fallenPinCount == MIN_PIN_COUNT;
    }

    public String symbol() {
        return State.of(this.fallenPinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return fallenPinCount == pin.fallenPinCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPinCount);
    }
}
