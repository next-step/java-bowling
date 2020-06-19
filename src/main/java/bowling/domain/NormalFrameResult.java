package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

public class NormalFrameResult implements FrameResult {
    private static final int MIN_NUMBER_OF_HIT_PIN = 0;
    private static final int MAX_NUMBER_OF_HIT_PIN = 9;

    private int firstNumberOfHitPin;

    NormalFrameResult(int firstNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
    }

    public static NormalFrameResult firstThrow(int numberOfHitPin) {
        validate(numberOfHitPin);

        return new NormalFrameResult(numberOfHitPin);
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
}
