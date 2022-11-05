package bowling.domain;

import java.util.List;
import java.util.function.Predicate;

public enum ScoreType {

    MISS(score -> score.frameEnd() &&
            score.calculate() < 10),
    SPARE(score -> score.frameEnd() &&
            score.getPin(score.pitches() - 2) != 10 &&
            score.calculate() == 10),
    STRIKE(score -> score.lastPin() == 10);

    private final Predicate<Score> predicate;

    ScoreType(final Predicate<Score> predicate) {

        this.predicate = predicate;
    }

    public boolean matches(final Score score) {

        return predicate.test(score);
    }

    public boolean strikeOrSpare() {

        return List.of(ScoreType.STRIKE, ScoreType.SPARE)
                .contains(this);
    }
}
