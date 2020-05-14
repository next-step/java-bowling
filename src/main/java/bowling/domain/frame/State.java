package bowling.domain.frame;

public enum State {
    STRIKE,
    SPARE,
    MISS,
    GUTTER;

    public static final int PIN_COUNT_GUTTER = 0;
    public static final int PIN_COUNT_STRIKE = 10;

    public static final State valueOf(int remainPinCount,
                                      int knockedDownPinCount) {
        if (remainPinCount == knockedDownPinCount) {
            return SPARE;
        }

        if (knockedDownPinCount == PIN_COUNT_GUTTER) {
            return GUTTER;
        }

        if (knockedDownPinCount == PIN_COUNT_STRIKE) {
            return STRIKE;
        }

        return MISS;
    }

    public static final State valueOf(int knockedDownPinCount) {
        if (knockedDownPinCount == PIN_COUNT_GUTTER) {
            return GUTTER;
        }

        if (knockedDownPinCount == PIN_COUNT_STRIKE) {
            return STRIKE;
        }

        return MISS;
    }
}
