package bowling.domain;

import bowling.domain.exceptions.ExceedMaxNumberOfHitPinSumException;
import bowling.domain.exceptions.InvalidNumberOfHitPinException;

import java.util.Objects;

public class NormalFrameResult implements FrameResult {
    private static final int MIN_NUMBER_OF_HIT_PIN = 0;
    private static final int MAX_NUMBER_OF_FIRST_HIT_PIN = 9;
    private static final int MAX_NUMBER_OF_SECOND_HIT_PIN = 10;

    private Integer firstNumberOfHitPin;
    private Integer secondNumberOfHitPin;

    NormalFrameResult(Integer firstNumberOfHitPin, Integer secondNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
    }

    public static NormalFrameResult firstThrow(int numberOfHitPin) {
        validateFirstThrow(numberOfHitPin);

        return new NormalFrameResult(numberOfHitPin, null);
    }

    public NormalFrameResult secondThrow(int numberOfHitPin) {
        validateSecondThrow(numberOfHitPin);

        return new NormalFrameResult(this.firstNumberOfHitPin, numberOfHitPin);
    }

    public boolean isCompleted() {
        return (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null);
    }

    private void validateSecondThrow(int numberOfHitPin) {
        if (numberOfHitPin < MIN_NUMBER_OF_HIT_PIN) {
            throw new InvalidNumberOfHitPinException("맞춘 핀의 수가 0 ~ 9를 벗어났습니다.");
        }

        if (this.firstNumberOfHitPin + numberOfHitPin > MAX_NUMBER_OF_SECOND_HIT_PIN) {
            throw new ExceedMaxNumberOfHitPinSumException("맞춘 핀 수의 총합은 10을 넘을 수 없습니다.");
        }
    }

    private static void validateFirstThrow(int numberOfHitPin) {
        if (numberOfHitPin < MIN_NUMBER_OF_HIT_PIN || numberOfHitPin > MAX_NUMBER_OF_FIRST_HIT_PIN) {
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
