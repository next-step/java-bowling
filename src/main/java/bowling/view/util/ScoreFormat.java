package bowling.view.util;

import bowling.domain.score.Score;
import bowling.domain.score.TurnScore;

public class ScoreFormat {
    private static final String MISS = "-";
    private static final String STRIKE = "X";

    private final Score score;

    public ScoreFormat(final Score score) {
        this.score = score;
    }

    public String format() {
        if (score instanceof TurnScore) {
            return toTurnScoreFormat((TurnScore) score);
        }
        return String.valueOf(score.value());
    }

    private String toTurnScoreFormat(TurnScore turnScore) {
        if (turnScore.isAllClear()) {
            return STRIKE;
        }
        if (turnScore.isZero()) {
            return MISS;
        }
        return String.valueOf(turnScore.value());
    }
}
