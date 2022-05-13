package bowling.model.frame;

import bowling.utility.Assert;

import java.util.Objects;

public final class Score {

    private static final int MINIMUM_VALUE = 0;
    private static final int DEFAULT_VALUE = MINIMUM_VALUE;
    private static final int DECREASE_REST_COUNT_STEP = 1;

    private final int value;
    private final int restCount;

    private Score(int value, int restCount) {
        Assert.isTrue(isEqualOrGreaterThanMin(value), String.format("score value(%d) must be equal or greater than %d", value, MINIMUM_VALUE));
        Assert.isTrue(isEqualOrGreaterThanMin(restCount), String.format("restCount(%d) must be equal or greater than %d", restCount, MINIMUM_VALUE));
        this.value = value;
        this.restCount = restCount;
    }

    static Score of(int value, int restCount) {
        return new Score(value, restCount);
    }

    static Score init(int restCount) {
        return of(DEFAULT_VALUE, restCount);
    }

    Score addValue(int value) {
        validateRestCount();
        return of(this.value + value, restCount - DECREASE_REST_COUNT_STEP);
    }

    boolean hasRestCount() {
        return restCount > MINIMUM_VALUE;
    }

    public Score changeRestCount(int restCount) {
        return of(value, restCount);
    }

    public int value() {
        return value;
    }

    private void validateRestCount() {
        if (restCount <= MINIMUM_VALUE) {
            throw new IllegalStateException(String.format("restCount(%d) must be greater than zero to add value", restCount));
        }
    }

    private boolean isEqualOrGreaterThanMin(int value) {
        return MINIMUM_VALUE <= value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, restCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value && restCount == score.restCount;
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", restCount=" + restCount +
                '}';
    }
}
