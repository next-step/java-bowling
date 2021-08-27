package bowling.domain.score;

import bowling.exception.CannotCalculateException;

public class Score {

    private static final int SCORE_COUNT_ZERO = 0 ;

    private final int score;
    private final int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score of(int pinsCount, int left) {
        return new Score(pinsCount, left);
    }

    public int getScore() {
        if (!canCalculateScore()) {
            throw new CannotCalculateException();
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == SCORE_COUNT_ZERO;
    }
}
