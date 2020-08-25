package bowling.domian.frame;

import bowling.domian.frame.exception.ScoreCalculateDoneException;

public class Score {
    private static final String SCORE_CALCULATE_DONE_ERROR_MSG = "점수 계산이 끝났습니다!";

    private static final int INITIAL_LEFT_COUNT = 0;
    private static final int STRIKE_LEFT_COUNT = 2;
    private static final int SPARE_LEFT_COUNT = 1;

    private final int score;
    private final int left;

    private Score(int falledPinsCount) {
        this(falledPinsCount, INITIAL_LEFT_COUNT);
    }

    private Score(int falledPinsCount, int left) {
        this.score = falledPinsCount;
        this.left = left;
    }

    public static Score strike() {
        return new Score(10, STRIKE_LEFT_COUNT);
    }

    public static Score spare() {
        return new Score(10, SPARE_LEFT_COUNT);
    }

    public static Score miss(int falledPinsCount) {
        return new Score(falledPinsCount);
    }

    public boolean isCalculateDone() {
        return left == INITIAL_LEFT_COUNT;
    }

    public int getScore() {
        return score;
    }

    public Score additionalBowl(int falledPinsCount) {
        if (isCalculateDone()) {
            throw new ScoreCalculateDoneException(SCORE_CALCULATE_DONE_ERROR_MSG);
        }

        return new Score(
                this.score + falledPinsCount,
                this.left - 1);
    }
}
