package bowling.domain.frame;

import java.util.Objects;

public class FrameIndex {

    public static final int MIN_INDEX = 1;
    public static final int MAX_INDEX = 10;

    private final int index;

    private FrameIndex(int index) {
        this.index = index;
    }

    public static FrameIndex first() {
        return new FrameIndex(MIN_INDEX);
    }
    public static FrameIndex last() {
        return new FrameIndex(MAX_INDEX);
    }

    public FrameIndex next() {
        if (index + 1 > MAX_INDEX) {
            throw new IllegalArgumentException(String.format("%d프레임을 초과할 수 없습니다.", FrameIndex.MAX_INDEX));
        }
        return new FrameIndex(index + 1);
    }

    public int getIndex() {
        return index;
    }

    public boolean lastBeforeIndex() {
        return index + 1 == MAX_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameIndex that = (FrameIndex) o;
        return getIndex() == that.getIndex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex());
    }

    @Override
    public String toString() {
        return "FrameIndex{" +
                "index=" + index +
                '}';
    }
}
