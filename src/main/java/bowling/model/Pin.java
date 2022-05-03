package bowling.model;

public final class Pin {

    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;

    private final int count;

    private Pin(int count) {
        validate(count);
        this.count = count;
    }

    public static Pin from(int count) {
        return new Pin(count);
    }

    private void validate(int count) {
        if (isLessThanMin(count) || isGreaterThanMax(count)) {
            throw new IllegalArgumentException(String.format("count(%d) must be between %d and %d", count, MINIMUM_COUNT, MAXIMUM_COUNT));
        }
    }

    private boolean isGreaterThanMax(int count) {
        return MAXIMUM_COUNT < count;
    }

    private boolean isLessThanMin(int count) {
        return count < MINIMUM_COUNT;
    }
}
