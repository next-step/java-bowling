package bowling.domain.score;

import java.util.Objects;

public final class LeftCount {

    private static final int NO_LEFT_COUNT = 0;
    private static final int STRIKE_LEFT_COUNT = 2;
    private static final int SPARE_LEFT_COUNT = 1;

    private final int leftCount;

    public LeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public static LeftCount from(int leftCount) {
        return new LeftCount(leftCount);
    }

    public static LeftCount create() {
        return new LeftCount(NO_LEFT_COUNT);
    }

    public static LeftCount strike() {
        return new LeftCount(STRIKE_LEFT_COUNT);
    }

    public static LeftCount spare() {
        return new LeftCount(SPARE_LEFT_COUNT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeftCount leftCount1 = (LeftCount) o;
        return leftCount == leftCount1.leftCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftCount);
    }
}
