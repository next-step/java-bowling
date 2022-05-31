package bowling.domain.state;

import java.util.OptionalInt;

public enum StateScore {
    STRIKE(state -> {

        return OptionalInt.empty();
    }),
    SPARE(state -> {

        return OptionalInt.empty();
    }),
    MISS(state -> {

        return OptionalInt.empty();
    });

    private final ScoreCalculator scoreCalculator;

    StateScore(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }
}
