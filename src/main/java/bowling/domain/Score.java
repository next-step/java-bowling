package bowling.domain;

import java.util.Objects;

public class Score {

    public static final int TOTAL_SCORE = 10;
    public static final int MISS_LEFT = 0;
    public static final int SPARE_LEFT = 1;
    public static final int STRIKE_LEFT = 2;

    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int score) {
        return new Score(score, MISS_LEFT);
    }

    public static Score ofSpare() {
        return new Score(TOTAL_SCORE, SPARE_LEFT);
    }

    public static Score ofStrike() {
        return new Score(TOTAL_SCORE, STRIKE_LEFT);
    }

    public void addScore(int score) {
        this.score += score;
        this.left--;
    }

    public boolean isEndCalculate() {
        return left == 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && left == score1.left;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, left);
    }
}
