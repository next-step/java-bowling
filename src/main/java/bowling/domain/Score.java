package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Score {

    private static final int MINIMUM_SINGLE_SCORE = 0;
    private static final int MAXIMUM_SINGLE_SCORE = 10;

    private static final int INIT_SCORE = 0;
    private static final int INIT_BONUS = 2;

    private int score;
    private int bonus;

    public Score() {
        score = INIT_SCORE;
        bonus = INIT_BONUS;
    }

    public void addScore(int score) {
        if (!validScore(score)) {
            throw new CustomException(ErrorCode.INVALID_SCORE);
        }
        if (!hasBonus()) {
            throw new CustomException(ErrorCode.INVALID_SCORE_ADDITION);
        }
        this.score += score;
        bonus--;
    }

    public void addBonus() {
        this.bonus++;
    }

    public int score() {
        return score;
    }

    public boolean hasBonus() {
        return bonus > 0;
    }

    private boolean validScore(int score) {
        return score >= MINIMUM_SINGLE_SCORE && score <= MAXIMUM_SINGLE_SCORE;
    }

}
