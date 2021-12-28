package bowling.domain;

import qna.domain.Score;

import java.util.Objects;

public class Pins {
    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;

    private final int value;

    public Pins(int value) {
        this.value = value;
    }

    public static Pins create(int value) {
        validateRange(value);
        return new Pins(value);
    }

    private static void validateRange(int value) {
        if (value < MIN_PINS || value > MAX_PINS) {
            throw new IllegalArgumentException(String.format("볼링 핀은 %d <= x <= %d 사이만 가능합니다.", MIN_PINS, MAX_PINS));
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isStrike() {
        return value == MAX_PINS;
    }

    public boolean isMiss(Pins other) {
        return Math.addExact(value, other.value) < MAX_PINS;
    }

    public boolean isSpare(Pins other) {
        return Math.addExact(value, other.value) == MAX_PINS;
    }

    public boolean isValidSum(Pins other) {
        return Math.addExact(value, other.value) <= MAX_PINS;
    }

    public boolean isGutter() {
        return value == MIN_PINS;
    }

    public Score score() {
        return Score.withNonLeftScore(value);
    }

    public Score score(Pins other) {
        return Score.withNonLeftScore(Math.addExact(value, other.value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return value == pins.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Pins{" +
                "value=" + value +
                '}';
    }
}
