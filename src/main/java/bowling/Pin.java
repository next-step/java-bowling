package bowling;

import java.util.Objects;

public class Pin {
    public static final int MIN_PIN_COUNT = 0;
    public static final int MAX_PIN_COUNT = 10;

    private final int knockDownCount;

    private Pin(int knockDownCount) {
        if (knockDownCount < MIN_PIN_COUNT || knockDownCount > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("쓰러트린 핀의 수가 유효범위에 들지 않았습니다.");
        }
        this.knockDownCount = knockDownCount;
    }

    public static Pin of(int knockDownCount) {
        return new Pin(knockDownCount);
    }

    public Pin add(Pin pin) {
        return Pin.of(knockDownCount + pin.knockDownCount);
    }

    public boolean areNoPinsDown() {
        return knockDownCount == MIN_PIN_COUNT;
    }

    public boolean areAllPinsDown() {
        return knockDownCount == MAX_PIN_COUNT;
    }

    public int pin() {
        return knockDownCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return knockDownCount == pin.knockDownCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(knockDownCount);
    }

    @Override
    public String toString() {
        return "Pin{" +
                "knockDownCount=" + knockDownCount +
                '}';
    }

}
