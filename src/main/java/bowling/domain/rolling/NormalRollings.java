package bowling.domain.rolling;

import bowling.domain.exception.InvalidThrowBallException;
import bowling.domain.frame.State;

public class NormalRollings {
    private static final int KNOCKED_DOWN_PIN_COUNT_DEFAULT = -1;
    private static final int KNOCKED_DOWN_PIN_COUNT_STRIKE = 10;

    private Integer firstRollingResult;
    private Integer secondRollingResult;

    private NormalRollings() {
        this.firstRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
        this.secondRollingResult = KNOCKED_DOWN_PIN_COUNT_DEFAULT;
    }

    public static NormalRollings init() {
        return new NormalRollings();
    }

    public void roll(int pinCount) {
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

    public boolean isState(State strike) {
        return false;
    }
}
