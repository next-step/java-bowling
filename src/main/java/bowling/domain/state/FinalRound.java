package bowling.domain.state;

import java.util.ArrayList;
import java.util.List;

public final class FinalRound {

    private static final int START = 0;
    private static final int ORIGINAL_FINISH = 2;
    private static final int BONUS_FINISH = 3;

    private static final List<FinalRound> CACHE;

    private final int opportunity;

    static {
        CACHE = new ArrayList<>();
        for (int opportunity = START; opportunity <= BONUS_FINISH; opportunity++) {
            CACHE.add(new FinalRound(opportunity));
        }
    }

    private FinalRound(final int opportunity) {
        this.opportunity = opportunity;
    }

    public static final FinalRound initialize() {
        return CACHE.get(START);
    }
}
