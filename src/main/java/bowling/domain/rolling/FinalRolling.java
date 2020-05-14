package bowling.domain.rolling;

import bowling.domain.exception.InvalidThrowBallException;

public class FinalRolling {
    private static final int KNOCKED_DOWN_PIN_COUNT_DEFAULT = -1;
    private static final int KNOCKED_DOWN_PIN_COUNT_STRIKE = 10;

    private Integer firstRollingResult;
    private Integer secondRollingResult;
    private Integer thirdRollingResult;

    private FinalRolling() {
        this.firstRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.secondRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.thirdRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
    }

    public static FinalRolling init() {
        return new FinalRolling();
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
