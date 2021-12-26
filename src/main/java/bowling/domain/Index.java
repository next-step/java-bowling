package bowling.domain;

import java.util.Objects;

public class Index {
    public static final int MIN_INDEX = 1;
    public static final int MAX_INDEX = 10;

    private final int value;

    private Index(int value) {
        this.value = value;
    }

    public static Index of(int value) {
        return new Index(value);
    }

    public static Index first() {
        return new Index(MIN_INDEX);
    }

    public Index next() {
        int nextIndex = value + 1;
        return Index.of(nextIndex);
    }

    public int getValue() {
        return value;
    }

    public boolean max() {
        return value == MAX_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return value == index.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
