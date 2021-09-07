package bowling;

import static bowling.CommonConstans.*;

public class Score {


    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score of(int score) {
        validMaxScore(score);
        validMinScore(score);
        return new Score(score);
    }

    public Score add(int score) {
        return new Score(this.score + score);
    }

    public int score() {
        return score;
    }

    private static void validMinScore(int score) {
        if (MIN_SCORE > score) {
            throw new IllegalArgumentException(MIN_UNDER_SCORE);
        }
    }

    private static void validMaxScore(int score) {
        if (MAX_SCORE < score) {
            throw new IllegalArgumentException(MAX_OVER_SCORE);
        }
    }
}
