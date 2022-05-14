package bowling.model.frame;

import bowling.utility.Assert;

import java.util.Objects;

public final class Score {

    private static final int MINIMUM_VALUE = 0;
    private static final int DEFAULT_VALUE = MINIMUM_VALUE;
    private static final int DECREASE_REMAIN_COUNT_STEP = 1;

    private final int value;
    private final int remainCount;

    private Score(int value, int remainCount) {
        Assert.isTrue(isEqualOrGreaterThanMin(value), String.format("score value(%d) must be equal or greater than %d", value, MINIMUM_VALUE));
        Assert.isTrue(isEqualOrGreaterThanMin(remainCount), String.format("remainCount(%d) must be equal or greater than %d", remainCount, MINIMUM_VALUE));
        this.value = value;
        this.remainCount = remainCount;
    }

    static Score of(int value, int remainCount) {
        return new Score(value, remainCount);
    }

    static Score init(int remainCount) {
        return of(DEFAULT_VALUE, remainCount);
    }

    Score addValue(int value) {
        validateRemainCount();
        return of(this.value + value, remainCount - DECREASE_REMAIN_COUNT_STEP);
    }

    boolean hasRemainCount() {
        return remainCount > MINIMUM_VALUE;
    }

    public Score changeRemainCount(int remainCount) {
        return of(value, remainCount);
    }

    public int value() {
        return value;
    }

    private void validateRemainCount() {
        if (remainCount <= MINIMUM_VALUE) {
            throw new IllegalStateException(String.format("remainCount(%d) must be greater than zero to add value", remainCount));
        }
    }

    private boolean isEqualOrGreaterThanMin(int value) {
        return MINIMUM_VALUE <= value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, remainCount);
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
        return value == score.value && remainCount == score.remainCount;
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", remainCount=" + remainCount +
                '}';
    }
}
