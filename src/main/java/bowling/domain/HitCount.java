package bowling.domain;

import bowling.exception.InputNumberOutOfBoundsException;

public final class HitCount {

    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;

    private final int count;

    public static final HitCount valueOf(final int count) {
        return new HitCount(count);
    }

    private HitCount(final int count) {
        validateBounds(count);
        this.count = count;
    }

    private final void validateBounds(final int count) {
        if (count < MINIMUM_COUNT || count > MAXIMUM_COUNT) {
            throw new InputNumberOutOfBoundsException(count);
        }
    }


}
