package bowling.score;

import bowling.exception.ExceptionMessage;

import java.util.Objects;

public class Score {
    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = 10;

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

        if (score < MIN_SCORE) {
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

    @Override
    public String toString() {
        return String.valueOf(score);
    }

    public Score sum(Score score) {
        if (score == null) {
            return this;
        }

        String sumScore = String.valueOf(this.score + score.score);
        return Score.of(sumScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score that = (Score) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
