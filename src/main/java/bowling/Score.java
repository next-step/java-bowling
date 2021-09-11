package bowling;

import java.util.Objects;

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
        return Score.of(this.score + score);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
