package bowling.domain.frame;

import bowling.domain.exception.InvalidThrowBallException;

public class NormalFrame {
    private static final int KNOCKED_DOWN_PIN_COUNT_DEFAULT = -1;
    private static final int KNOCKED_DOWN_PIN_COUNT_STRIKE = 10;

    private Integer firstRollingResult;
    private Integer secondRollingResult;

    public NormalFrame() {
        this.firstRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.secondRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
    }

    public void rollingBall(int pinCount) {
        if (isFrameEnd()) {
            throw new InvalidThrowBallException();
        }

        if ((firstRollingResult == KNOCKED_DOWN_PIN_COUNT_DEFAULT)) {
            this.firstRollingResult = pinCount;
            return;
        }

        this.secondRollingResult = pinCount;
    }

    public boolean isRollable() {
        if (isFrameEnd()) {
            return false;
        }

        if (firstRollingResult == KNOCKED_DOWN_PIN_COUNT_STRIKE) {
            return false;
        }

        return true;
    }

    private boolean isFrameEnd() {
        if (firstRollingResult != KNOCKED_DOWN_PIN_COUNT_DEFAULT &&
                secondRollingResult != KNOCKED_DOWN_PIN_COUNT_DEFAULT) {
            return true;
        }

        return false;
    }
}
