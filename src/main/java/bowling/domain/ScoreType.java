package bowling.domain;

import java.util.List;
import java.util.function.Predicate;

public enum ScoreType {
    STRIKE(score -> score.pinsSize() == 1 &&
            score.pinNumber(0) == 10),
    SPARE(score -> score.pinsSize() == 2 &&
            score.pinNumber(0) != 10 &&
            score.pinNumber(0) + score.pinNumber(1) == 10),
    FINAL_STRIKE(score -> score.pinsSize() == 2 &&
            score.pinNumber(1) == 10),
    FINAL_SPARE(score -> score.pinsSize() == 2 &&
            score.pinNumber(0) == 10 &&
            score.pinNumber(1) != 10),
    MISS(score -> score.pinsSize() == 2 &&
            score.pinNumber(0) + score.pinNumber(1) != 10),
    PROCEEDING(score -> score.pinsSize() < 2);

    private final Predicate<Score> predicate;

    ScoreType(Predicate<Score> predicate) {
        this.predicate = predicate;
    }

    public boolean matches(Score score) {
        return predicate.test(score);
    }

    public boolean isKnockedDowned() {
        return List.of(ScoreType.STRIKE, ScoreType.FINAL_STRIKE, ScoreType.SPARE, ScoreType.FINAL_SPARE).contains(this);
    }
}
