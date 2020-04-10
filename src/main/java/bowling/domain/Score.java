package bowling.domain;

import static bowling.Messages.WARNING_SCORE_NOT_ALLOWED_RANGE;

public class Score {
    private static final int RANGE_MAX = 10;
    private static final int RANGE_MIN = 0;

    private int score;

    public Score(int score) {
        validateRange(score);
        this.score = score;
    }

    private void validateRange(int score) {
        if (score > RANGE_MAX || score < RANGE_MIN) {
            throw new IllegalArgumentException(WARNING_SCORE_NOT_ALLOWED_RANGE);
        }
    }
}