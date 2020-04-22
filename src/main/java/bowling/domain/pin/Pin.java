package bowling.domain.pin;

import bowling.exception.BowlingException;

import java.util.Objects;

public class Pin {

    public static final String PINS_COUNT_RANGE = "핀은 0~10 사이여야 합니다.";
    public static final int MIN_PIN = 0;
    public static final int MAX_PIN = 10;

    private final int pins;

    public Pin(int pins) {
        validatePinesCount(pins);
        this.pins = pins;
    }

    public static Pin from() {
        return new Pin(MIN_PIN);
    }

    private void validatePinesCount(int pins) {
        if (pins < MIN_PIN || pins > MAX_PIN) {
            throw new BowlingException(PINS_COUNT_RANGE);
        }
    }

    public Pin bowl(final int count) {
        return new Pin(count);
    }

    public boolean isGutter() {
        return pins == MIN_PIN;
    }

    public boolean isFinish() {
        return pins == MAX_PIN;
    }

    public boolean isFinish(Pin other) {
        return (pins + other.pins) == MAX_PIN;
    }

    public int getDownPin() {
        return this.pins;
    }

    public int getTotalDownPin(Pin other) {
        int totalDown = pins + other.pins;
        validatePinesCount(totalDown);
        return totalDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin1 = (Pin) o;
        return pins == pin1.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
