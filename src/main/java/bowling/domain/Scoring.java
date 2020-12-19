package bowling.domain;

import java.util.Optional;

import static bowling.domain.BallThrow.MAX_PINS;

public enum Scoring {
    STRIKE, MISS, SPARE;

    Optional<Scoring> asOptional() {
        return Optional.of(this);
    }

    static Optional<Scoring> valueOf(Integer first, Integer second) {
        if (first == 10) {
            return STRIKE.asOptional();
        }

        if (second == null) {
            return Optional.empty();
        }

        if (first + second == MAX_PINS) {
            return SPARE.asOptional();
        }

        return MISS.asOptional();
    }
}
