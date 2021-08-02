package bowling.domain.score;

import java.util.Objects;

public class InProgressScore extends Score {
    private static final int LEFT_COUNT_LIMIT = 2;

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

    @Override
    public boolean isCalculable() {
        return false;
    }

    @Override
    public Score add(Score anotherScore) {
        if (leftCount == 1) {
            return CalculableScore.from(score + anotherScore.score);
        }

        return InProgressScore.init(score + anotherScore.score, leftCount - 1);
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
