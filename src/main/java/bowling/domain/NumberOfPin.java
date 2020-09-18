package bowling.domain;

import java.util.Objects;

public class NumberOfPin {

    public static final int MIN_NUMBER_OF_PIN = 0;
    public static final int MAX_NUMBER_OF_PIN = 10;

    private final int numberOfPin;

    public NumberOfPin(int numberOfPin) {
        if (numberOfPin < MIN_NUMBER_OF_PIN || numberOfPin > MAX_NUMBER_OF_PIN) {
            throw new IllegalArgumentException("0 ~ 10 사이의 값만 입력 가능합니다.");
        }
        this.numberOfPin = numberOfPin;
    }

    public int getNumberOfPin() {
        return numberOfPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfPin that = (NumberOfPin) o;
        return numberOfPin == that.numberOfPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPin);
    }

}
