package bowling.model;

import java.util.Objects;

public class Count {

    public static final int ZERO = 0;
    public static final int ONCE = 1;
    public static final int TWICE = 2;
    private static final int THIRD = 3;
    public static final Count COUNT_ZERO = Count.of(ZERO);
    public static final Count COUNT_THIRD = Count.of(THIRD);
    private int count;

    private Count(int count) {
        this.count = count;
    }

    public static Count of(int count) {
        return new Count(count);
    }

    int getCount() {
        return count;
    }

    public Count decrease() {
        return of(count - 1);
    }

    public Count increase() {
        return of(count + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count count1 = (Count) o;
        return count == count1.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "Count{" +
                "count=" + count +
                '}';
    }

    public boolean isMatch(Count other) {
        return this.count == other.count;
    }
}

