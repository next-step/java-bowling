package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

import java.util.Objects;

public class ThrowResult implements Comparable<ThrowResult> {
    private static final int MIN_NUMBER_OF_HIT_PIN = 0;
    private static final int MAX_NUMBER_OF_HIT_PIN = 10;
    private static final String ERROR_COMMENT = "한 번에 맞출수 있는 핀의 수는 0 ~ 10 사이여야 합니다.";

    private final int numberOfHitPin;

    public ThrowResult(int numberOfHitPin) {
        validate(numberOfHitPin);

        this.numberOfHitPin = numberOfHitPin;
    }

    public ThrowResult plus(ThrowResult throwResult) {
        return new ThrowResult(this.numberOfHitPin + throwResult.numberOfHitPin);
    }

    int getNumberOfHitPin() {
        return numberOfHitPin;
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
        ThrowResult that = (ThrowResult) o;
        return numberOfHitPin == that.numberOfHitPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfHitPin);
    }

    @Override
    public int compareTo(ThrowResult o) {
        return Integer.compare(this.numberOfHitPin, o.numberOfHitPin);
    }
}
