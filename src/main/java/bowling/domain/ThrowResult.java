package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

public class ThrowResult {
    private final int numberOfHitPin;

    public ThrowResult(int numberOfHitPin) {
        if (numberOfHitPin < 0 || numberOfHitPin > 10) {
            throw new InvalidNumberOfHitPinException("한 번에 맞출수 있는 핀의 수는 0 ~ 10 사이여야 합니다.");
        }

        this.numberOfHitPin = numberOfHitPin;
    }
}
