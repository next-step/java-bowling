package bowling.domain.score;

import bowling.domain.pin.Pin;
import bowling.exception.CannotCalculateException;

import java.util.Objects;

public final class Score {

    private final int score;
    private final LeftCount leftCount;

    private Score() {
        this(0, null);
    }

    private Score(int score, int leftCount) {
        this(score, LeftCount.from(leftCount));
    }

    private Score(int score, LeftCount leftCount) {
        this.score = score;
        this.leftCount = leftCount;
    }

    public static Score of(int score, int leftCount) {
        return new Score(score, leftCount);
    }

    public static Score notCalculable() {
        return new Score();
    }

    public static Score normal(int score) {
        return new Score(score, LeftCount.create());
    }

    public static Score strike() {
        return new Score(Pin.MAX_COUNT, LeftCount.strike());
    }

    public static Score spare() {
        return new Score(Pin.MAX_COUNT, LeftCount.spare());
    }

    public boolean canCalculate() {
        return leftCount != null && !leftCount.hasLeftCount();
    }

    public int calculate() {
        if (!canCalculate()) {
            throw new CannotCalculateException();
        }
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score && Objects.equals(leftCount, score1.leftCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, leftCount);
    }
}
