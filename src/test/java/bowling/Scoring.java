package bowling;

import java.util.Optional;

enum Scoring {
    STRIKE, MISS, GUTTER, SPARE;

    Optional<Scoring> asOptional() {
        return Optional.of(this);
    }
}
