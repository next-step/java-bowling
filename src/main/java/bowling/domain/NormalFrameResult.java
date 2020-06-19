package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

import java.util.Objects;

public class NormalFrameResult implements FrameResult {
    private static final int MIN_NUMBER_OF_HIT_PIN = 0;
    private static final int MAX_NUMBER_OF_HIT_PIN = 9;

    private Integer firstNumberOfHitPin;
    private Integer secondNumberOfHitPin;

    NormalFrameResult(Integer firstNumberOfHitPin, Integer secondNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
    }

    public static NormalFrameResult firstThrow(int numberOfHitPin) {
        validate(numberOfHitPin);

        return new NormalFrameResult(numberOfHitPin, null);
    }

    public NormalFrameResult secondThrow(int numberOfHitPin) {
        return new NormalFrameResult(this.firstNumberOfHitPin, numberOfHitPin);
    }

    public boolean isCompleted() {
        return (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null);
    }

    private static void validate(int numberOfHitPin) {
        if (numberOfHitPin < MIN_NUMBER_OF_HIT_PIN || numberOfHitPin > MAX_NUMBER_OF_HIT_PIN) {
            throw new InvalidNumberOfHitPinException("맞춘 핀의 수가 0 ~ 9를 벗어났습니다.");
        }
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameResult that = (NormalFrameResult) o;
        return firstNumberOfHitPin == that.firstNumberOfHitPin &&
                secondNumberOfHitPin == that.secondNumberOfHitPin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumberOfHitPin, secondNumberOfHitPin);
    }
}
