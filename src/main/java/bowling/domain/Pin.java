package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MIN_PIN_COUNT = 0;
    public static final int MAX_PIN_COUNT = 10;
    private static final String INVALID_PIN_COUNT = "잘못된 수를 입력했습니다.";
    private final int count;


    public Pin(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count < MIN_PIN_COUNT || count > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(INVALID_PIN_COUNT);
        }
    }

    public boolean isEnd() {
        return count == MAX_PIN_COUNT;
    }

    public boolean isSpare(int countOfPin) {
        return this.count + getCount() == MAX_PIN_COUNT;
    }


    public int sum(int countOfPin) {
        return count + countOfPin;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "-";
        }
        return String.valueOf(count);
    }
}
