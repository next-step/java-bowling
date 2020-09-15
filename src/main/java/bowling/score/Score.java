package bowling.score;

import bowling.exception.ExceptionMessage;

public class Score {
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 10;
    public static final Score MAX = new Score(MAX_SCORE);

    private int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(String scoreValue) {
        int score = validate(scoreValue);

        return new Score(score);
    }

    private static int validate(String scoreValue) {
        int score = Integer.parseInt(scoreValue);

        if(score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_SCORE_VALUE);
        }

        return score;
    }

    public boolean isStrike() {
        return this.score == MAX_SCORE;
    }

    public boolean isGutter() {
        return this.score == MIN_SCORE;
    }

    public int getScore() {
        return score;
    }
}
