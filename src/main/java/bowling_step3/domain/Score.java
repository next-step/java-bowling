package bowling_step3.domain;

import java.util.Objects;

import static bowling_step3.domain.Pitch.BOWLING_MAX_NUMBER;

public class Score {
    private static final int DEFAULT = 0;

    private int score;
    private int left;

    public Score() {
    }

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score of(int countOfKnockDown) {
        return new Score(score + countOfKnockDown, left - 1);
    }

    public static Score ofKnockDownState(int countOfKnockDown, int left) {
        return new Score(countOfKnockDown, left);
    }

    public boolean isFinishedCalculate() {
        return left == DEFAULT;
    }

    public void add(int score) {
        this.left--;
        this.score += score;
    }

    public int getScore() {
        return score;
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


