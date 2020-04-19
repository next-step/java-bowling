package bowling;

import java.util.Objects;

public class LeftScoreCount {

    private final int count;

    private LeftScoreCount(final int count) {
        validateCountRange(count);
        this.count = count;
    }

    private static void validateCountRange(final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Left Score count must be greater than zero");
        }
    }

    public static LeftScoreCount of(final int count) {
        return new LeftScoreCount(count);
    }

    public static LeftScoreCount of(final LeftScoreCount leftScoreCount) {
        return new LeftScoreCount(leftScoreCount.count);
    }

    public boolean isEqualTo(final int count) {
        return this.count == count;
    }

    public LeftScoreCount minus() {
        return new LeftScoreCount(count - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeftScoreCount)) return false;
        LeftScoreCount that = (LeftScoreCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
