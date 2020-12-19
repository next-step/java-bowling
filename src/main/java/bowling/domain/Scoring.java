package bowling.domain;

import static bowling.domain.BallThrow.MAX_PINS;

public enum Scoring {
    STRIKE, MISS, SPARE, NONE;

    static Scoring valueOf(Integer first, Integer second) {
        if (first == 10) {
            return STRIKE;
        }

        if (second == null) {
            return NONE;
        }

        if (first + second == MAX_PINS) {
            return SPARE;
        }

        return MISS;
    }
}
