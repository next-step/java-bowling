package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;

public class Score {
    public static final int MINIMUM_COUNT = 0;
    public static final int MAXIMUM_COUNT = 10;
    private final int count;

    public Score(int count) {
        validateOutOfRange(count);
        this.count = count;
    }

    private void validateOutOfRange(int count) {
        if (count < MINIMUM_COUNT || count > MAXIMUM_COUNT) {
            throw new BowlingGameException(ErrorMessage.SCORE_OUT_OF_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return count == score.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    public int getScore() {
        return this.count;
    }

    public Score add(Score score) {
        return new Score(score.count + this.count);
    }

    @Override
    public String toString() {
        return "Score{" +
                "count=" + count +
                '}';
    }
}
