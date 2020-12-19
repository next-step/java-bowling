package bowling;

import java.util.Optional;

import static bowling.BallThrow.MAX_PINS;
import static bowling.BallThrow.MIN_PINS;

enum Scoring {
    STRIKE, MISS, GUTTER, SPARE;

    Optional<Scoring> asOptional() {
        return Optional.of(this);
    }

    static Scoring nonStrikeValueOf(int sumOfFallingPins) {
        if (sumOfFallingPins == MAX_PINS) {
            return SPARE;
        }

        if (sumOfFallingPins == MIN_PINS) {
            return GUTTER;
        }

        return MISS;
    }
}
