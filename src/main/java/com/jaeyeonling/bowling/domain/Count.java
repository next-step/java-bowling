package com.jaeyeonling.bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Count {

    private static final Map<Integer, Count> CACHE = new HashMap<>();

    private static final int INCREMENT_VALUE = 1;
    private static final int DEFAULT = 0;

    private final int count;

    private Count(final int initialValue) {
        this.count = initialValue;
    }

    public static Count of() {
        return of(DEFAULT);
    }

    public static Count of(final int initialValue) {
        if (DEFAULT > initialValue) {
            throw new IllegalStateException();
        }

        return CACHE.computeIfAbsent(initialValue, Count::new);
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

    public boolean isLower(int count) {
        return this.count < count;
    }

    public boolean isDefault() {
        return count == DEFAULT;
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
