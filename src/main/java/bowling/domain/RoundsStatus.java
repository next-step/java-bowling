package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;

public enum RoundsStatus {
    NONE,
    STRIKE,
    SPARE,
    MISS;

    public static final int MAX_CLEAR_PIN_COUNT = 10;
    private static final int ZERO = 0;

    public boolean isNone() {
        return this == NONE;
    }

    public static RoundsStatus getRoundStatus(int roundIndex, int totalClearPinCount) {
        if (roundIndex == ZERO) {
            return firstRoundStatus(totalClearPinCount);
        }

        if (totalClearPinCount == MAX_CLEAR_PIN_COUNT) {
            return SPARE;
        }

        return MISS;
    }

    public boolean isStrike() {
        return this == STRIKE;
    }

    public boolean isSpare() {
        return this == SPARE;
    }

    private static RoundsStatus firstRoundStatus(int firstClearPinCount) {
        validClearPinCount(firstClearPinCount);

        if (firstClearPinCount == MAX_CLEAR_PIN_COUNT) {
            return STRIKE;
        }

        return NONE;
    }

    private static void validClearPinCount(int clearPinCount) {
        if (clearPinCount > MAX_CLEAR_PIN_COUNT) {
            throw new BowlingException(ExceptionType.INVALID_CLEAR_PIN_COUNT);
        }
    }
}
