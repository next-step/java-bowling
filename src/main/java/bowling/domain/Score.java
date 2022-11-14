package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;

public class Score {
    private final int count;

    public Score(int count) {
        validateOverMinimum(count);
        this.count = count;
    }

    private void validateOverMinimum(int score) {
        if (score < 0) {
            throw new BowlingGameException(ErrorMessage.SCORE_OUT_OF_RANGE);
        }
    }

    public Score add(Score score) {
        return new Score(count + score.count);
    }

    public boolean bigger(int count) {
        return this.count > count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return count == score.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
