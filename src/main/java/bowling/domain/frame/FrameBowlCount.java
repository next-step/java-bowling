package bowling.domain.frame;

import bowling.exception.FrameBowlCountOutOfRangeException;

import java.util.Objects;

public class FrameBowlCount {
    private static final int MIN_BOWL_COUNT = 0;
    private static final int MAX_BOWL_COUNT = 10;
    private final int count;

    public FrameBowlCount(final int count) {
        validRange(count);
        this.count = count;
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
        final FrameBowlCount that = (FrameBowlCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
