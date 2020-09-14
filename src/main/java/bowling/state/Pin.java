package bowling.state;

import bowling.exception.ExceptionMessage;

public class Pin {
    public static final int MAX = 10;

    private int value;

    public Pin(int value) {
        this.value = value;
    }

    public static Pin of(int value) {
        validate(value);
        return new Pin(value);
    }

    private static void validate(int value) {
        if(value > MAX) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BOWL_VALUE);
        }
    }

    public static boolean isStrike(int value) {
        return value == 10;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
