package bowling.domain;

import java.util.Objects;

public class NumberOfPins {

    public static final int MIN_NUMBER_OF_PINS = 0;
    public static final int MAX_NUMBER_OF_PINS = 10;

    private final int numberOfPins;

    public NumberOfPins(int numberOfPins) {
        if (numberOfPins < MIN_NUMBER_OF_PINS || numberOfPins > MAX_NUMBER_OF_PINS) {
            throw new IllegalArgumentException("0 ~ 10 사이의 값만 입력 가능합니다.");
        }
        this.numberOfPins = numberOfPins;
    }

    public int getNumberOfPins() {
        return numberOfPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfPins that = (NumberOfPins) o;
        return numberOfPins == that.numberOfPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPins);
    }

}
