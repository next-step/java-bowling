package bowling.view.util;

import bowling.domain.score.Score;
import bowling.domain.score.TurnScore;

public class ScoreFormat {
    private final Score score;

    public ScoreFormat(final Score score) {
        this.score = score;
    }

    public String format() {
        if (score.isEmpty()) {
            return Text.EMPTY.toString();
        }
        if (score instanceof TurnScore) {
            return toTurnScoreFormat((TurnScore) score);
        }
        return String.valueOf(score.value());
    }

    private String toTurnScoreFormat(TurnScore turnScore) {
        if (turnScore.isAllClear()) {
            return Text.STRIKE.toString();
        }
        if (turnScore.isZero()) {
            return Text.MISS.toString();
        }
        return String.valueOf(turnScore.value());
    }

    private enum Text {
        EMPTY(""), MISS("-"), STRIKE("X");

        private final String text;

        Text(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
