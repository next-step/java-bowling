package com.jaeyeonling.bowling.domain.count;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Count {

    private static final Map<Integer, Count> CACHE = new HashMap<>();

    private static final int INCREMENT_VALUE = 1;
    static final int MIN = 0;

    private final int count;

    private Count(final int initialValue) {
        this.count = initialValue;
    }

    public static Count of() {
        return of(MIN);
    }

    public static Count of(final int initialValue) {
        if (MIN > initialValue) {
            throw new ShorterThanMinCountException(initialValue);
        }

        return CACHE.computeIfAbsent(initialValue, Count::new);
    }

    public int getCount() {
        return count;
    }

    public Count up() {
        return Count.of(count + INCREMENT_VALUE);
    }

    public Count down() {
        return Count.of(count - INCREMENT_VALUE);
    }

    public Count sum(final Count other) {
        return of(count + other.count);
    }

    public boolean isHigherAndEquals(final int count) {
        return this.count >= count;
    }

    public boolean isLowerAndEquals(final int count) {
        return this.count <= count;
    }

    public boolean isMin() {
        return count == MIN;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Count)) {
            return false;
        }

        final Count that = (Count) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
