package bowling.view.util;

import bowling.domain.score.Score;
import bowling.domain.score.TurnScore;

public class ScoreFormat {
    private static final String MISS = "-";
    private static final String STRIKE = "-";

    private final Score score;

    public ScoreFormat(final Score score) {
        this.score = score;
    }

    public String format() {
        if (score instanceof TurnScore && ((TurnScore) score).isAllClear()) {
            return STRIKE;
        }
        return score.isZero() ? MISS : String.valueOf(score.value());
    }
}
