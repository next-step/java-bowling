package com.seok2.bowling.frame.domain;

import java.util.Objects;

public class Remaining {

    private static final int DECREMENT_VALUE = 1;
    public static final Remaining INFINITY = Remaining.of(3);
    public static final Remaining STRIKE = Remaining.of(2);
    public static final Remaining SPARE = Remaining.of(1);
    public static final Remaining COVER = Remaining.of(1);
    public static final Remaining ZERO = Remaining.of(0);

    private int remaining;

    private Remaining(int remaining) {
        this.remaining = remaining;
    }

    public static Remaining of(int remaining) {
        return new Remaining(remaining);
    }

    public Remaining decrement() {
        return of(remaining - DECREMENT_VALUE);
    }

    public boolean isZero() {
        return this.equals(ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Remaining)) {
            return false;
        }
        Remaining that = (Remaining) o;
        return remaining == that.remaining;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remaining);
    }
}
