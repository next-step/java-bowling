package bowling.domain.score;

import bowling.exception.InvalidBonusCountSizeException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.subtractExact;

public final class BonusCount {

    private static final int MINIMUM_BONUS_COUNT = 0;
    private static final int MAXIMUM_BONUS_COUNT = 2;
    private static final int ONE = 1;

    private static final List<BonusCount> CACHE;

    private final int remain;

    static {
        CACHE = new ArrayList<>();
        for (int remain = MINIMUM_BONUS_COUNT; remain <= MAXIMUM_BONUS_COUNT; remain++) {
            CACHE.add(new BonusCount(remain));
        }
    }

    public static final BonusCount none() {
        return CACHE.get(MINIMUM_BONUS_COUNT);
    }

    public static final BonusCount spare() {
        return CACHE.get(ONE);
    }

    public static final BonusCount strike() {
        return CACHE.get(MAXIMUM_BONUS_COUNT);
    }

    private BonusCount(final int remain) {
        validateSize(remain);
        this.remain = remain;
    }

    private final void validateSize(final int remain) {
        if (remain < MINIMUM_BONUS_COUNT || remain > MAXIMUM_BONUS_COUNT) {
            throw new InvalidBonusCountSizeException();
        }
    }

    public final boolean isFinish() {
        return remain == MINIMUM_BONUS_COUNT;
    }

    public final BonusCount decrease() {
        return CACHE.get(subtractExact(remain, ONE));
    }
}
