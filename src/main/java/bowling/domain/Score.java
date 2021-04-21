package bowling.domain;

import java.util.Objects;

public class Score {

    public static final int FULL_SCORE = 10;
    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public static Score ofMiss(int score) {
        return new Score(score, 0);
    }

    public static Score ofSpare() {
        return new Score(FULL_SCORE, 1);
    }

    public static Score ofStrike() {
        return new Score(FULL_SCORE, 2);
    }

    public void addScore(Pin pinCount) {
        this.left--;
        this.score += pinCount.getCount();
    }
    public int getScore() {
        return score;
    }

    public boolean isEndCalculate() {
        return left == 0;
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
