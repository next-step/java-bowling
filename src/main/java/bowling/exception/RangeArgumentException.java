package bowling.exception;

public class RangeArgumentException extends IllegalArgumentException {
    private static final String LESS_AND_GREATER = "%d 작거나 %d 크면 안됩니다.";
    private static final String ONLY_EQUAL = "%d 와 같아야합니다";
    private static final String LESS = "%d 보다 작아야합니다";

    public RangeArgumentException(String message) {
        super(message);
    }

    public static RangeArgumentException notAvailableLessAndGreater(int min, int max) {
        return new RangeArgumentException(String.format(LESS_AND_GREATER, min, max));
    }

    public static RangeArgumentException onlyEqualTo(int value) {
        return new RangeArgumentException(String.format(ONLY_EQUAL, value));
    }

    public static RangeArgumentException lessThan(int value) {
        return new RangeArgumentException(String.format(LESS, value));
    }

}
