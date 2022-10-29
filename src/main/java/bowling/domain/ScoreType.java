package bowling.domain;

import java.util.List;
import java.util.function.Predicate;

public enum ScoreType {
    STRIKE(pins -> pins.size() == 1 &&
            pins.get(0).count() == 10),
    SPARE(pins -> pins.size() == 2 &&
            pins.get(0).count() != 10 &&
            pins.get(0).count() + pins.get(1).count() == 10),
    FINAL_STRIKE(pins -> pins.size() == 2 &&
            pins.get(1).count() == 10),
    FINAL_SPARE(pins -> pins.size() == 2 &&
            pins.get(0).count() == 10 &&
            pins.get(1).count() != 10),
    MISS(pins -> pins.size() == 2 &&
            pins.get(0).count() + pins.get(1).count() != 10),
    PROCEEDING(pins -> pins.size() < 2);

    private final Predicate<List<Pin>> predicate;

    ScoreType(Predicate<List<Pin>> predicate) {
        this.predicate = predicate;
    }

    public boolean matches(List<Pin> pins) {
        return predicate.test(pins);
    }

    public boolean isKnockedDowned() {
        return List.of(ScoreType.STRIKE, ScoreType.FINAL_STRIKE, ScoreType.SPARE, ScoreType.FINAL_SPARE).contains(this);
    }
}
