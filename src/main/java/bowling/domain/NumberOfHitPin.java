package bowling.domain;

import bowling.domain.exceptions.ExceedLimitOfNumberOfHitPinException;

import java.util.Objects;

public class NumberOfHitPin implements Comparable<NumberOfHitPin> {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;

    private final int value;

    public NumberOfHitPin(int value) {
        validate(value);

        this.value = value;
    }

    public NumberOfHitPin plus(NumberOfHitPin numberOfHitPin) {
        return new NumberOfHitPin(this.value + numberOfHitPin.value);
    }

    private void validate(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new ExceedLimitOfNumberOfHitPinException("생성 가능한 범위를 벗어났습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberOfHitPin that = (NumberOfHitPin) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(NumberOfHitPin o) {
        return Integer.compare(this.value, o.value);
    }
}
