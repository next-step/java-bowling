package bowling.domain;

import java.util.Objects;

public class Pin {
    private static final int PIN_MIN_NUMBER = 1;
    private static final int PIN_MAX_NUMBER = 10;

    private final int number;

    private Pin(int number) {
        this.number = number;
    }

    public static Pin from(int number) {
        validateNumber(number);
        return new Pin(number);
    }

    private static void validateNumber(int number) {
        if (number < PIN_MIN_NUMBER || number > PIN_MAX_NUMBER) {
            throw new IllegalArgumentException("유효하지 않는 핀 번호입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return number == pin.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Pin{" +
                "number=" + number +
                '}';
    }
}
