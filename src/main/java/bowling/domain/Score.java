package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;

public class Score {
    private int score;
    private int bonusCount;

    public Score(int score) {
        validateOverMinimum(score);
        this.score = score;
        this.bonusCount = 0;
    }

    public Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    private void validateOverMinimum(int score) {
        if (score < 0) {
            throw new BowlingGameException(ErrorMessage.SCORE_OUT_OF_RANGE);
        }
    }

    public void add(Score score) {
        this.score += score.score;
    }

    public boolean isEnd() {
        return bonusCount == 0;
    }

    public void addBonus(Pin pin) {
        this.score += pin.getScore();
        this.bonusCount--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score1 = (Score) o;
        return score == score1.score && bonusCount == score1.bonusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, bonusCount);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", bonusCount=" + bonusCount +
                '}';
    }
}
