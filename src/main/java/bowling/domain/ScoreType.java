package bowling.domain;

import java.util.List;
import java.util.function.Predicate;

public enum ScoreType {
    STRIKE(pins -> pins.size() == 1 &&
            pins.get(0).count() == 10),
    SPARE(pins -> pins.size() == 2 &&
            pins.get(0).count() != 10 &&
            pins.get(0).count() + pins.get(1).count() == 10),
    MISS(pins -> pins.size() == 2 &&
            pins.get(0).count() + pins.get(1).count() != 10),
    PROCEEDING(pins -> pins.size() < 2),
    FINAL_STRIKE(pins -> pins.size() == 2 &&
            pins.get(1).count() == 10),
    FINAL_SPARE(pins -> pins.size() == 2 &&
            pins.get(0).count() == 10 &&
            pins.get(1).count() != 10);

    private final Predicate<List<Pin>> operate;

    ScoreType(Predicate<List<Pin>> operate) {
        this.operate = operate;
    }

    public boolean matches(List<Pin> pins) {
        return operate.test(pins);
    }

    public boolean isKnockedDown() {
        return List.of(ScoreType.STRIKE, ScoreType.FINAL_STRIKE, ScoreType.SPARE).contains(this);
    }
}
