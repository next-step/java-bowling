package bowling.model;

import java.util.Objects;

public class Pin {
    private static final int MAX_POINT = 10;
    private static final int MIN_POINT = 0;

    private final int pin;

    public Pin(int pin) {
        isValidRange(pin);
        this.pin = pin;
    }

    public static void isValidRange(int pinCount) {
        if(pinCount < MIN_POINT || pinCount > MAX_POINT) {
            throw new IllegalArgumentException("핀 갯수를 1개 이상 10개 이하로 입력해주세요.");
        }
    }

    public Pin add(int pin) {
        return new Pin(this.pin + pin);
    }

    public boolean isStrike() {
        return pin == MAX_POINT;
    }

    public boolean isGutter() {
        return pin == MIN_POINT;
    }

    @Override
    public String toString() {
        return (isGutter()) ? "-" : String.valueOf(pin);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin1 = (Pin) o;
        return pin == pin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
