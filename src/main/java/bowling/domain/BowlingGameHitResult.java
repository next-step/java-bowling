package bowling.domain;

import java.util.List;

public enum BowlingGameHitResult {
    GUTTER(hits -> hits.get(hits.size() - 1) == BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS),
    MISS(isMiss()),
    SPARE(isSpare()),
    STRIKE(hits -> hits.get(hits.size() - 1) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS),
    NONE(hits -> true),
    ;

    private static BowlingGameHitResultIdentification isSpare() {
        return hits -> {
            if (hits.size() < 2) {
                return false;
            }

            int indexOfHit = hits.size() - 1;
            int remainedPins = BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS - hits.stream()
                    .reduce(0, Integer::sum) % BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
            return hits.get(indexOfHit - 1) + hits.get(indexOfHit) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS
                    && remainedPins == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
        };
    }

    private static BowlingGameHitResultIdentification isMiss() {
        return hits -> {
            if (hits.size() < 2) {
                return false;
            }

            int indexOfHit = hits.size() - 1;
            return hits.get(indexOfHit - 1) + hits.get(indexOfHit) < BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
        };
    }

    private static final List<BowlingGameHitResult> ALL_VALUES = List.of(values());

    private final BowlingGameHitResultIdentification identification;

    BowlingGameHitResult(BowlingGameHitResultIdentification identification) {
        this.identification = identification;
    }

    public static BowlingGameHitResult from(List<Integer> hits) {
        return ALL_VALUES.stream()
                .filter(result -> result.identification.identify(hits))
                .findFirst()
                .orElse(NONE);
    }

}
