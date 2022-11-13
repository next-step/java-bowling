package bowling.domain;

import java.util.List;

public class Spare implements BowlingGameHitState {

    @Override
    public boolean identify(List<Integer> hits) {
        if (hits.size() < 2) {
            return false;
        }

        int indexOfHit = hits.size() - 1;
        int remainedPins = BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS - sum(hits) % BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
        return hits.get(indexOfHit - 1) + hits.get(indexOfHit) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS
                && remainedPins == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    private int sum(List<Integer> hits) {
        return hits.stream()
                .reduce(0, Integer::sum);
    }

}
