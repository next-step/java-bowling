package bowling.domain;

import java.util.List;

public class Strike implements BowlingGameHitState {

    private static final int NUMBER_OF_BONUS = 2;

    @Override
    public boolean identify(List<Integer> hits) {
        return hits.get(hits.size() - 1) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    @Override
    public int getNumberOfBonus() {
        return NUMBER_OF_BONUS;
    }

}
