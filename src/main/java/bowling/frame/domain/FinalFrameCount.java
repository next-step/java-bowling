package bowling.frame.domain;

import java.util.Objects;

public class FinalFrameCount {

    private static final int MIN = 0;
    private static final int MAX = 3;
    private int count;

    public FinalFrameCount(int count) {
        this.count = count;
    }

    public static FinalFrameCount of() {
        return new FinalFrameCount(MIN);
    }

    public static FinalFrameCount of(int count) {
        return new FinalFrameCount(count);
    }

    public FinalFrameCount increment() {
        count++;
        return this;
    }

    public boolean isMax() {
        return count == MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinalFrameCount)) {
            return false;
        }
        FinalFrameCount that = (FinalFrameCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

}
