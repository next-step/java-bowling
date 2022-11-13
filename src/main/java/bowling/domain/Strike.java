package bowling.domain;

import java.util.List;

public class Strike implements BowlingGameHitState {

    @Override
    public boolean identify(List<Integer> hits) {
        return hits.get(hits.size() - 1) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

}
