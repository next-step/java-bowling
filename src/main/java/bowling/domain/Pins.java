package bowling.domain;

import bowling.domain.roll.Roll;
import bowling.domain.roll.RollType;

import static bowling.domain.BowlingErrorMessage.INVALID_PIN_COUNT_RANGE;
import static com.google.common.base.Preconditions.checkArgument;

public class Pins {

    public static final int DEFAULT_PIN_COUNT = 10;
    public static final int MAX_ROLL_COUNT = 2;

    private int count = DEFAULT_PIN_COUNT;
    private int countOfRoll = 0;

    public Roll knockedDown(int countOfPins) {
        checkArgument(countOfPins <= count, INVALID_PIN_COUNT_RANGE);
        checkArgument(countOfPins >= 0 && countOfPins <= DEFAULT_PIN_COUNT, INVALID_PIN_COUNT_RANGE);

        count -= countOfPins;
        RollType rollType = RollType.valueOf(countOfPins, count);
        countOfRoll++;

        return new Roll(rollType, countOfPins);
    }

    public boolean isAllPinsDown() {
        return count == 0;
    }

    public boolean cannotRollMore() {
        return countOfRoll == MAX_ROLL_COUNT;
    }

    public boolean isNotAvailable() {
        return isAllPinsDown() || cannotRollMore();
    }

    public int getCountOfRemain() {
        return count;
    }

    public void reset() {
        count = DEFAULT_PIN_COUNT;
        countOfRoll = 0;
    }
}
