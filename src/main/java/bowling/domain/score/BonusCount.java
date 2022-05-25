package bowling.domain.score;

import bowling.exception.InvalidBonusCountException;

import java.util.HashMap;
import java.util.Map;

public class BonusCount {

    private static final int MIN_BONUS_COUNT = 0;

    private static final int ONE = 1;

    private static final int MAX_BONUS_COUNT = 2;

    private static final Map<Integer, BonusCount> BONUS_COUNTS;

    private final int remain;

    static {
        BONUS_COUNTS = new HashMap<>();
        BONUS_COUNTS.putIfAbsent(MIN_BONUS_COUNT, new BonusCount(MIN_BONUS_COUNT));
        BONUS_COUNTS.putIfAbsent(ONE, new BonusCount(ONE));
        BONUS_COUNTS.putIfAbsent(MAX_BONUS_COUNT, new BonusCount(MAX_BONUS_COUNT));
    }

    private BonusCount(int remain) {
        validateSize(remain);
        this.remain = remain;
    }

    private void validateSize(int remain) {
        if (remain < MIN_BONUS_COUNT || remain > MAX_BONUS_COUNT) {
            throw new InvalidBonusCountException(remain);
        }
    }

    public static BonusCount strike() {
        return BONUS_COUNTS.get(MAX_BONUS_COUNT);
    }

    public static BonusCount spare() {
        return BONUS_COUNTS.get(ONE);
    }

    public static BonusCount none() {
        return BONUS_COUNTS.get(MIN_BONUS_COUNT);
    }

    public boolean isEmpty() {
        return remain == MIN_BONUS_COUNT;
    }

    public BonusCount decrease() {
        int decreasedRemain = remain - ONE;
        if (decreasedRemain < MIN_BONUS_COUNT) {
            throw new InvalidBonusCountException(decreasedRemain);
        }
        return BONUS_COUNTS.get(decreasedRemain);
    }

}
