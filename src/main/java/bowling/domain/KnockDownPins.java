package bowling.domain;

public class KnockDownPins {
    public static final String INVALID_KNOCK_DOWN_PINS_VALUE = "유효하지 않은 knockDownPins 입력값입니다.";
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;

    private final int value;

    private KnockDownPins(int value) {
        validateInputValue(value);
        this.value = value;
    }

    private void validateInputValue(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(INVALID_KNOCK_DOWN_PINS_VALUE);
        }
    }

    public static KnockDownPins valueOf(int value) {
        return new KnockDownPins(value);
    }
}
