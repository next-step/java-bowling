package bowling.domain.score;

import java.util.Objects;

import static bowling.domain.pin.DownedPins.MAX_NUM_OF_DOWNED_PINS;

public class InProgressScore extends Score {
    private static final int LEFT_COUNT_LIMIT = 2;
    private static final int ZERO = 0;

    private final int leftCount;

    protected InProgressScore(int score, int leftCount) {
        super(score);

        validate(leftCount);

        this.leftCount = leftCount;
    }

    private void validate(int leftCount) {
        if (leftCount > LEFT_COUNT_LIMIT) {
            throw new IllegalArgumentException("Left count can't exceed " + LEFT_COUNT_LIMIT);
        }
    }

    public static InProgressScore init(int score, int leftCount) {
        return new InProgressScore(score, leftCount);
    }

    public static Score ofStrike() {
        return new InProgressScore(MAX_NUM_OF_DOWNED_PINS, LEFT_COUNT_LIMIT);
    }

    @Override
    public boolean isCalculable() {
        return false;
    }

    @Override
    public Score add(Score anotherScore) {
        int nextLeftCount = leftCount - 1;

        if (nextLeftCount == ZERO) {
            return CalculableScore.from(score + anotherScore.score);
        }

        return InProgressScore.init(score + anotherScore.score, nextLeftCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InProgressScore that = (InProgressScore) o;
        return leftCount == that.leftCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leftCount);
    }
}
