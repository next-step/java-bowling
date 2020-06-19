package bowling.domain;

import bowling.domain.exceptions.InvalidNumberOfHitPinException;

public class NormalFrameResult implements FrameResult {
    private int firstNumberOfHitPin;

    NormalFrameResult(int firstNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
    }

    public static NormalFrameResult firstThrow(int numberOfHitPin) {
        if (numberOfHitPin < 0 || numberOfHitPin > 9) {
            throw new InvalidNumberOfHitPinException("맞춘 핀의 수가 0 ~ 9를 벗어났습니다.");
        }

        return new NormalFrameResult(numberOfHitPin);
    }

    @Override
    public boolean isStrike() {
        return false;
    }
}
