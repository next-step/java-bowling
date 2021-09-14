package bowling.domain;

import bowling.exception.InvalidScoreValueException;

import java.util.Objects;

public class Score {

    private static final int MIN = 0;
    private static final int MAX = 300;

    private final String score;

    private Score(final int score) {
        this.score = String.valueOf(score);
    }

    private Score(final String score) {
        this.score = score;
    }

    public static Score from(final int score) {
        requireAboveZero(score);
        return new Score(score);
    }

    private static void requireAboveZero(final int score) {
        if (score < MIN || score > MAX) {
            throw new InvalidScoreValueException(MIN, MAX);
        }
    }

    public static Score noScore() {
        return new Score("");
    }

    public String score() {
        return score;
    }

    public boolean isEmpty() {
        return score.isEmpty();
    }

    public Score plus(final Score score) {
        if (score.isEmpty() || this.isEmpty()) {
            return noScore();
        }
        return new Score(this.intValue() + score.intValue());
    }

    private int intValue() {
        return Integer.parseInt(score);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Score score1 = (Score) o;
        return Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
