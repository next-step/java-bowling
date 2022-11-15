package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;

public class Score {
    private final int score;
    private final int bonusCount;

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

    public Score add(Score score) {
        return new Score(this.score + score.score);
    }

    public boolean bigger(int count) {
        return this.score > count;
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
}
