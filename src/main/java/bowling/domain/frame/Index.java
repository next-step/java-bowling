package bowling.domain.frame;

import java.util.Objects;
import java.util.stream.IntStream;

public class Index {

    public static final int MAX_INDEX = 10;
    private static final int MIN_INDEX = 1;
    private static final int INCREMENT_NUM = 1;
    private static final int END_NUM = 9;

    private static final Index[] indexCache = IntStream.rangeClosed(MIN_INDEX, MAX_INDEX)
            .mapToObj(Index::new)
            .toArray(Index[]::new);

    private int idx;

    private Index(int idx) {
        this.idx = idx;
    }

    public static Index first() {
        return of(MIN_INDEX);
    }

    public static Index of(int idx) {
        try{
            return indexCache[--idx];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Frame Index 범위는 1보다 작거나 10보다 클 수 없습니다.");
        }
    }

    public Index increment() {
        return of(idx + INCREMENT_NUM);
    }

    public boolean isLast() {
        return idx == END_NUM;
    }

    public int getIndex() {
        return this.idx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return idx == index.idx;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }
}
