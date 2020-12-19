package bowling.domain;

import java.util.Optional;

import static bowling.domain.BallThrow.MAX_PINS;
import static bowling.domain.BallThrow.MIN_PINS;

public enum Scoring {
    STRIKE, MISS, SPARE;

    Optional<Scoring> asOptional() {
        return Optional.of(this);
    }

    static Scoring nonStrikeValueOf(int sumOfFallingPins) {
        if (sumOfFallingPins > MAX_PINS || sumOfFallingPins < MIN_PINS) {
            throw new IllegalArgumentException();
        }

        if (sumOfFallingPins == MAX_PINS) {
            return SPARE;
        }

        return MISS;
    }
}
