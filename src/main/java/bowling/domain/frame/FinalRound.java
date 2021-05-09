package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.addExact;

public final class FinalRound {

    private static final int INCREASE_UNIT = 1;

    private static final int START = 0;
    private static final int ORIGINAL_FINISH = 2;
    private static final int BONUS_FINISH = 3;

    private static final List<FinalRound> CACHE;

    private final int round;

    static {
        CACHE = new ArrayList<>();
        for (int round = START; round <= BONUS_FINISH; round++) {
            CACHE.add(new FinalRound(round));
        }
    }

    private FinalRound(final int round) {
        this.round = round;
    }

    public static final FinalRound initialize() {
        return CACHE.get(START);
    }

    public final FinalRound next() {
        return CACHE.get(addExact(round, INCREASE_UNIT));
    }

    public final boolean isFinish(final boolean bonus) {
        if ((!bonus && round == ORIGINAL_FINISH) || (bonus && round == BONUS_FINISH)) {
            return true;
        }
        return false;
    }

}
