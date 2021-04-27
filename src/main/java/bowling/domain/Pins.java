package bowling.domain;

import bowling.exception.InputOverHitCountException;

import java.util.Objects;

public final class Pins {

    private static final int MAXIMUM_COUNT = 10;
    private static final int MINIMUM_COUNT = 0;
    public static final int CLEAR = 0;

    private int remain;

    private Pins() {
        this(MAXIMUM_COUNT);
    }

    private Pins(final int remain) {
        this.remain = remain;
    }

    public static final Pins init() {
        return new Pins();
    }

    public final Pins hit(final int hitCount) {
        validateOverCount(hitCount);
        return new Pins(Math.subtractExact(remain, hitCount));
    }

    private final void validateOverCount(int hitCount) {
        if (remain < hitCount) {
            throw new InputOverHitCountException(hitCount, remain);
        }
    }

    public final boolean isClear() {
        if (remain == CLEAR) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return remain == pins.remain;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remain);
    }


}
