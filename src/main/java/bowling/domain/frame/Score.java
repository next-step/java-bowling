package bowling.domain.frame;

import java.util.Objects;

public class Score {
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = 0;
    private int score;
    private int left;

    private Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int score) {
        return new Score(score, 0);
    }

    public static Score ofSpare() {
        return new Score(MAX_SCORE, 1);
    }

    public static Score ofStrike() {
        return new Score(MAX_SCORE, 2);
    }

    public Score bowl(int fallenPins) {
        return new Score(this.score + fallenPins, this.left - 1);
    }

    public int getScore() {
        if (!isCalculation()) {
            throw new IllegalArgumentException();
        }
        return this.score;
    }

    public boolean isCalculation() {
        return left == MIN_SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score &&
                left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
