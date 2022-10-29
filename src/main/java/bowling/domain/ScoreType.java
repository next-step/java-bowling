package bowling.domain;

import java.util.List;
import java.util.function.Predicate;

public enum ScoreType {

    MISS(score -> score.pinsSize() >= 2 &&
            score.pinNumber(score.pinsSize() - 2) + score.lastPinNumber() < 10 ||
            (score.pinsSize() == 3 && score.pinNumber(0) + score.pinNumber(1) == 10 && score.lastPinNumber() < 10)),
    SPARE(score -> score.pinsSize() >= 2 &&
            score.pinNumber(score.pinsSize() - 2) != 10 &&
            score.pinNumber(score.pinsSize() - 2) + score.lastPinNumber() == 10),
    STRIKE(score -> score.lastPinNumber() == 10);

    private final Predicate<Score> predicate;

    ScoreType(Predicate<Score> predicate) {
        this.predicate = predicate;
    }

    public boolean matches(Score score) {
        return predicate.test(score);
    }

    public boolean isKnockedDowned() {
        return List.of(ScoreType.STRIKE, ScoreType.SPARE).contains(this);
    }
}
