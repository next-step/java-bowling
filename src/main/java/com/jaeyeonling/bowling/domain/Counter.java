package com.jaeyeonling.bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Counter {

    private static final Map<Integer, Counter> CACHE = new HashMap<>();

    private static final int INCREMENT_VALUE = 1;
    private static final int DEFAULT = 0;

    private final int count;

    private Counter(final int initialValue) {
        this.count = initialValue;
    }

    public static Counter of() {
        return of(DEFAULT);
    }

    public static Counter of(final int initialValue) {
        if (DEFAULT > initialValue) {
            throw new IllegalStateException();
        }

        return CACHE.computeIfAbsent(initialValue, Counter::new);
    }

    public Counter up() {
        return new Counter(count + INCREMENT_VALUE);
    }

    public Counter down() {
        return new Counter(count - INCREMENT_VALUE);
    }

    public boolean isHigherAndEquals(final int count) {
        return this.count >= count;
    }

    public boolean isLowerAndEquals(final int count) {
        return this.count <= count;
    }

    public Counter sum(final Counter other) {
        return of(count + other.count);
    }

    public boolean isDefault() {
        return count == DEFAULT;
    }
}
