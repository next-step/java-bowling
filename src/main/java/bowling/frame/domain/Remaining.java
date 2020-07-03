package bowling.frame.domain;

import java.util.Objects;

public class Remaining {

    private static final int ZERO_VALUE = 0;
    private static final int SPARE = 1;
    private static final int STRIKE = 2;
    private static final int MAX = 3;
    private int remaining;

    private Remaining(int remaining) {
        this.remaining = remaining;
    }

    public static Remaining of(int remaining) {
        return new Remaining(remaining);
    }

    public static Remaining ofZero() {
        return of(ZERO_VALUE);
    }

    public static Remaining ofSpare() {
        return of(SPARE);
    }

    public static Remaining ofStrike() {
        return of(STRIKE);
    }

    public static Remaining ofInfinity() {
        return of(MAX);
    }

    public void decrement() {
        this.remaining--;
    }

    public boolean isZero() {
        return remaining == ZERO_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remaining remaining1 = (Remaining) o;
        return remaining == remaining1.remaining;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remaining);
    }
}
