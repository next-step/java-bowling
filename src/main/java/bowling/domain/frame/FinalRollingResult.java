package bowling.domain.frame;

import bowling.domain.exception.InvalidThrowBallException;

public class FinalRollingResult {
    private static final int KNOCKED_DOWN_PIN_COUNT_DEFAULT = -1;
    private static final int KNOCKED_DOWN_PIN_COUNT_STRIKE = 10;

    private Integer firstRollingResult;
    private Integer secondRollingResult;
    private Integer thirdRollingResult;

    private FinalRollingResult() {
        this.firstRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.secondRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.thirdRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
    }

    public static FinalRollingResult init() {
        return new FinalRollingResult();
    }

    public void roll(int pinCount) {
        if (isFrameEnd()) {
            throw new InvalidThrowBallException();
        }

        if (firstRollingResult == KNOCKED_DOWN_PIN_COUNT_DEFAULT) {
            this.firstRollingResult = pinCount;
            return;
        }

        if (secondRollingResult == KNOCKED_DOWN_PIN_COUNT_DEFAULT) {
            this.secondRollingResult = pinCount;
            return;
        }

        this.thirdRollingResult = pinCount;
    }


    public boolean isRollable() {
        if (isFrameEnd()) {
            return false;
        }

        return true;
    }

    private boolean isFrameEnd() {
        if (firstRollingResult == KNOCKED_DOWN_PIN_COUNT_STRIKE) {
            return true;
        }

        if (firstRollingResult != KNOCKED_DOWN_PIN_COUNT_DEFAULT &&
                secondRollingResult != KNOCKED_DOWN_PIN_COUNT_DEFAULT) {
            return !shouldBonus();
        }

        return false;
    }

    private boolean shouldBonus() {
        if (firstRollingResult == KNOCKED_DOWN_PIN_COUNT_STRIKE) {
            return true;
        }

        if (firstRollingResult + secondRollingResult == KNOCKED_DOWN_PIN_COUNT_STRIKE) {
            return true;
        }

        return false;
    }
}
