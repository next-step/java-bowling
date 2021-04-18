package bowling.domain.frame;

import java.util.Objects;

public class Pin {

    public static final int MIN_PIN_COUNT = 0;
    public static final int MAX_PIN_COUNT = 10;

    private final int count;

    public Pin(){
        this.count = 0;
    }

    public Pin(int count) {
        validation(count);
        this.count = count;
    }

    private static void validation(int pinCount) {
        checkMinimumSizeNumber(pinCount);
        checkOverSizeNumber(pinCount);
    }

    private static void checkMinimumSizeNumber(int pinCount) {
        if (pinCount < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 0개 미만일 수 없습니다.");
        }
    }

    private static void checkOverSizeNumber(int pinCount) {
        if (pinCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 10개를 초과할 수 없습니다.");
        }
    }

    public static Pin from(int count) {
        validation(count);
        return new Pin(count);
    }

    public int getCount() {
        return count;
    }

    public String convertString() {
        return String.valueOf(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
