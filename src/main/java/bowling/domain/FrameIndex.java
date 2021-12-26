package bowling.domain;

import java.util.Objects;

public class FrameIndex {
    public static final int MIN_INDEX = 1;
    public static final int MAX_INDEX = 10;

    private final int value;

    public FrameIndex(int value) {
        validate(value);
        this.value = value;
    }

    public static FrameIndex create(int value) {
        return new FrameIndex(value);
    }

    public static FrameIndex first() {
        return new FrameIndex(MIN_INDEX);
    }

    public static FrameIndex last() {
        return new FrameIndex(MAX_INDEX);
    }

    public FrameIndex next() {
        if (value + 1 > MAX_INDEX) {
            throw new IllegalStateException(String.format("다음 인덱스는 최대 값(%d)을 초과 합니다.", MAX_INDEX));
        }
        return FrameIndex.create(value + 1);
    }

    public int getValue() {
        return value;
    }

    public boolean isMax() {
        return value == MAX_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameIndex that = (FrameIndex) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private void validate(int value) {
        if (value < MIN_INDEX || value > MAX_INDEX) {
            throw new IllegalStateException(String.format("인덱스는 %d <= x <= %d 범위의 값이어야 합니다.", MIN_INDEX, MAX_INDEX));
        }
    }
}
