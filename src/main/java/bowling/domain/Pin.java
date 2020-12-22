package bowling.domain;

import java.util.Objects;

public class Pin {

    public static final int MIN_PIN_COUNT = 0;
    public static final int MAX_PIN_COUNT = 10;
    public static final String MIN_PIN_SIZE_ERROR = "핀 개수에 음수가 올수 없습니다.";
    public static final String MAX_PIN_SIZE_ERROR = String.format("핀 개수는 %s개를 초과 할 수 없습니다.", MAX_PIN_COUNT);

    private final int pinCount;

    private Pin(int pinCount) {
        this.pinCount = pinCount;
    }

    public static Pin from(int pinCount) {
        validation(pinCount);
        return new Pin(pinCount);
    }

    private static void validation(int pinCount) {
        checkMinimumSizeNumber(pinCount);
        checkOverSizeNumber(pinCount);
    }

    private static void checkMinimumSizeNumber(int pinCount) {
        if (pinCount < MIN_PIN_COUNT) {
            throw new IllegalArgumentException(MIN_PIN_SIZE_ERROR);
        }
    }

    private static void checkOverSizeNumber(int pinCount) {
        if (pinCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException(MAX_PIN_SIZE_ERROR);
        }
    }

    public int getPinCount() {
        return pinCount;
    }

    public String getCountToString() {
        return String.valueOf(pinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return pinCount == pin.pinCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinCount);
    }
}
