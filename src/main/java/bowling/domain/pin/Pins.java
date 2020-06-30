package bowling.domain.pin;

import java.util.Objects;

public class Pins {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int countOfPins;

    private Pins(int countOfPins) {
        validate(countOfPins);
        this.countOfPins = countOfPins;
    }

    private void validate(int countOfPins) {
        if (countOfPins < MIN_PINS || countOfPins > MAX_PINS) {
            throw new IllegalArgumentException("볼링핀의 갯수는 0 에서 10 까지만 가능합니다.");
        }
    }

    public static Pins of(int countOfPins) {
        return new Pins(countOfPins);
    }

    public static int sum(Pins firstPins, Pins secondPins) {
        return firstPins.countOfPins + secondPins.countOfPins;
    }

    public boolean isStrike() {
        return countOfPins == MAX_PINS;
    }

    @Override
    public String toString() {
        if (countOfPins == MIN_PINS) {
            return "-";
        }

        if (isStrike()) {
            return "X";
        }

        return Integer.toString(countOfPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return countOfPins == pins1.countOfPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins);
    }
}
