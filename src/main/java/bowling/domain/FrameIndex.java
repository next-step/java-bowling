package bowling.domain;

import java.util.Objects;

public class FrameIndex {
    public static final int MIN = 1;
    public static final int MAX = 10;

    private final int index;

    private FrameIndex(int index) {
        this.index = index;
    }

    public static FrameIndex of(int value) {
        return new FrameIndex(value);
    }

    public static FrameIndex first() {
        return new FrameIndex(MIN);
    }

    public FrameIndex next() {
        int nextIndex = index + 1;
        return FrameIndex.of(nextIndex);
    }

    public int getIndex() {
        return index;
    }

    public boolean max() {
        return index == MAX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameIndex index = (FrameIndex) o;
        return this.index == index.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
