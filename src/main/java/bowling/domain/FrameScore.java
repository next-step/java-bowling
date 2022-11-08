package bowling.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum FrameScore {
    STRIKE(Scores::isStrike),
    SPARE(Scores::isSpare),
    ONGOING(scores -> scores.getTryCount() == 1),
    FINISH(scores -> scores.getTryCount() == 2);

    private final Predicate<Scores> predicate;

    FrameScore(Predicate<Scores> predicate) {
        this.predicate = predicate;
    }

    public static FrameScore from(Scores scores) {
        return Arrays.stream(values())
                .filter(vo -> vo.predicate.test(scores))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}