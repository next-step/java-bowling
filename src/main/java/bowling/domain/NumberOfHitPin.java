package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

import java.util.Objects;

public class NumberOfHitPin implements Comparable<NumberOfHitPin> {
    private static final int MIN_NUMBER_OF_HIT_PIN = 0;
    private static final int MAX_NUMBER_OF_HIT_PIN = 10;
    private static final String ERROR_COMMENT = "한 번에 맞출수 있는 핀의 수는 0 ~ 10 사이여야 합니다.";

    private final int numberOfHitPin;

    public NumberOfHitPin(int numberOfHitPin) {
        validate(numberOfHitPin);

        this.numberOfHitPin = numberOfHitPin;
    }

    public NumberOfHitPin plus(NumberOfHitPin numberOfHitPin) {
        return new NumberOfHitPin(this.numberOfHitPin + numberOfHitPin.numberOfHitPin);
    }

    private void validate(int numberOfHitPin) {
        if (numberOfHitPin < MIN_NUMBER_OF_HIT_PIN || numberOfHitPin > MAX_NUMBER_OF_HIT_PIN) {
            throw new InvalidNumberOfHitPinException(ERROR_COMMENT);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfHitPin that = (NumberOfHitPin) o;
        return numberOfHitPin == that.numberOfHitPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfHitPin);
    }

    @Override
    public String toString() {
        return "NumberOfHitPin{" +
                "numberOfHitPin=" + numberOfHitPin +
                '}';
    }

    @Override
    public int compareTo(NumberOfHitPin o) {
        return Integer.compare(this.numberOfHitPin, o.numberOfHitPin);
    }
}
