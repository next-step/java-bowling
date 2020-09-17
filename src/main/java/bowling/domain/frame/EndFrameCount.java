package bowling.domain.frame;

import java.util.Objects;

public class EndFrameCount {

    private static final int MIN = 0;
    private static final int MAX = 3;
    private int count;

    private EndFrameCount(int count) {
        this.count = count;
    }

    public static EndFrameCount of() {
        return new EndFrameCount(MIN);
    }

    public static EndFrameCount of(int count) {
        return new EndFrameCount(count);
    }

    public EndFrameCount increment() {
        count++;
        return this;
    }

    public boolean isMax() {
        return count == MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndFrameCount that = (EndFrameCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
