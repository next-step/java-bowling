package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;

public enum RoundsStatus {
    NONE,
    STRIKE,
    SPARE,
    MISS;

    private static final int STRIKE_PIN_COUNT = 10;

    public static RoundsStatus firstRoundStatus(int firstClearPinCount) {
        validClearPinCount(firstClearPinCount);

        if (firstClearPinCount == STRIKE_PIN_COUNT) {
            return STRIKE;
        }

        return NONE;
    }

    public static RoundsStatus getRoundStatus(int totalClearPinCount) {
        validClearPinCount(totalClearPinCount);

        if (totalClearPinCount == STRIKE_PIN_COUNT) {
            return SPARE;
        }

        return MISS;
    }

    private static void validClearPinCount(int clearPinCount) {
        if (clearPinCount > STRIKE_PIN_COUNT) {
            throw new BowlingException(ExceptionType.INVALID_CLEAR_PIN_COUNT);
        }
    }
}
