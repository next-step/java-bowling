package bowling_step3.domain;

import java.util.Objects;

import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Score {
    private static final int COUNT_OF_STRIKE = 2;

    private static final int COUNT_OF_SPARE = 1;

    private static final int DEFAULT = 0;

    private int score;

    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int score) {
        return new Score(score, DEFAULT);
    }

    public static Score ofSpare() {
        return new Score(BOWLING_MAX_NUMBER, COUNT_OF_SPARE);
    }

    public static Score ofStrike() {
        return new Score(BOWLING_MAX_NUMBER, COUNT_OF_STRIKE);
    }

    public void add(int score) {
        this.left--;
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public boolean isFinishedCalculate() {
        return left == DEFAULT;
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


