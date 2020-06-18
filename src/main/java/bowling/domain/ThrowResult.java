package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

public class ThrowResult {
    private static final int MIN_NUMBER_OF_HIT_PIN = 0;
    private static final int MAX_NUMBER_OF_HIT_PIN = 10;
    private static final String ERROR_COMMENT = "한 번에 맞출수 있는 핀의 수는 0 ~ 10 사이여야 합니다.";

    private final int numberOfHitPin;

    public ThrowResult(int numberOfHitPin) {
        validate(numberOfHitPin);

        this.numberOfHitPin = numberOfHitPin;
    }

    private void validate(int numberOfHitPin) {
        if (numberOfHitPin < MIN_NUMBER_OF_HIT_PIN || numberOfHitPin > MAX_NUMBER_OF_HIT_PIN) {
            throw new InvalidNumberOfHitPinException(ERROR_COMMENT);
        }
    }
}
