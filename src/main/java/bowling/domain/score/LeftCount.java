package bowling.domain.score;

import java.util.Objects;

public final class LeftCount {

    private final int leftCount;

    public LeftCount(int leftCount) {
        this.leftCount = leftCount;
    }

    public static LeftCount from(int leftCount) {
        return new LeftCount(leftCount);
    }

    public static LeftCount create() {
        return null;
    }

    public static LeftCount strike() {
        return null;
    }

    public static LeftCount spare() {
        return null;
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
