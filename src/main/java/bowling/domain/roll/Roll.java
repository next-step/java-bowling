package bowling.domain.roll;

import static bowling.domain.BowlingErrorMessage.INVALID_PIN_COUNT_RANGE;
import static bowling.domain.Pins.DEFAULT_PIN_COUNT;
import static com.google.common.base.Preconditions.checkArgument;

public class Roll {

    private final RollType type;
    private final int countOfKnockedPins;

    public Roll(RollType type, int countOfKnockedPins) {
        checkArgument(countOfKnockedPins >= 0 && countOfKnockedPins <= DEFAULT_PIN_COUNT,
                INVALID_PIN_COUNT_RANGE);

        this.type = type;
        this.countOfKnockedPins = countOfKnockedPins;
    }

    public int getCountOfKnockedPins() {
        return countOfKnockedPins;
    }

    public boolean isStrike() {
        return type == RollType.STRIKE;
    }

    public boolean isSpare() {
        return type == RollType.SPARE;
    }

    public String getMarking() {
        return type.marking(countOfKnockedPins);
    }
}
