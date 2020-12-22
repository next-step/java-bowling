package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class KnockDownPins {
    public static final String INVALID_KNOCK_DOWN_PINS_VALUE = "유효하지 않은 knockDownPins 입력값입니다.";
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 10;
    private static final Map<Integer, KnockDownPins> knockDownPinsByInteger = new HashMap<>();
    static {
        for (int value = MIN_VALUE; value <= MAX_VALUE; value++) {
            knockDownPinsByInteger.put(value, new KnockDownPins(value));
        }
    }

    private final int value;

    private KnockDownPins(int value) {
        this.value = value;
    }

    public static KnockDownPins valueOf(int value) {
        validateInputValue(value);
        return knockDownPinsByInteger.get(value);
    }

    private static void validateInputValue(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(INVALID_KNOCK_DOWN_PINS_VALUE);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KnockDownPins)) return false;

        KnockDownPins that = (KnockDownPins) o;

        return getValue() == that.getValue();
    }

    @Override
    public int hashCode() {
        return getValue();
    }

    @Override
    public String toString() {
        return "KnockDownPins{" +
                "value=" + value +
                '}';
    }
}
