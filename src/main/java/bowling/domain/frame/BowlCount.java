package bowling.domain.frame;

import bowling.exception.FrameBowlCountOutOfRangeException;

import java.util.Objects;

public class BowlCount {
    private static final int MIN_BOWL_COUNT = 0;
    private static final int MAX_BOWL_COUNT = 10;

    private final int bowlCount;

    public BowlCount(final int bowlCount) {
        validRange(bowlCount);
        this.bowlCount = bowlCount;
    }

    public int count() {
        return bowlCount;
    }

    public boolean isGreaterThan(final long count) {
        return this.bowlCount < count;
    }

    private void validRange(final int count) {
        if (count < MIN_BOWL_COUNT || count > MAX_BOWL_COUNT) {
            throw new FrameBowlCountOutOfRangeException(count);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BowlCount that = (BowlCount) o;
        return bowlCount == that.bowlCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bowlCount);
    }
}
