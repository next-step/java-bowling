package bowling.domain;

import bowling.exception.InvalidBonusCountSizeException;

public final class BonusCount {

    private static final int MINIMUM_BONUS_COUNT = 0;
    private static final int MAXIMUM_BONUS_COUNT = 2;

    private final int remain;

    public static final BonusCount valueOf(final int remain) {
        return new BonusCount(remain);
    }

    private BonusCount(final int remain) {
        validateSize(remain);
        this.remain = remain;
    }

    private void validateSize(final int remain) {
        if (remain < MINIMUM_BONUS_COUNT || remain > MAXIMUM_BONUS_COUNT) {
            throw new InvalidBonusCountSizeException();
        }
    }

    public final boolean isFinish() {
        return remain == MINIMUM_BONUS_COUNT;
    }
}
