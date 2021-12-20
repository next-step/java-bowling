package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.domain.Pin.PIN_MIN_NUMBER;

public class Pins {
    public static final int PINS_MIN_COUNT = 0;
    public static final int PINS_MAX_COUNT = 10;

    private final List<Pin> pins = new ArrayList<>();

    private Pins() {
        init(PINS_MAX_COUNT);
    }

    private Pins(int count) {
        init(count);
    }

    private void init(int count) {
        for (int number = PIN_MIN_NUMBER; number <= count; number++) {
            pins.add(Pin.from(number));
        }
        validatePinsSize();
    }

    public static Pins create() {
        return new Pins();
    }

    public static Pins create(int count) {
        return new Pins(count);
    }

    public Pins fallDown(Pins fallDownPins) {
        int fallDownCount = fallDownPins.size();
        validateFallDownCount(fallDownCount);
        int startSize = size();
        while (falling(startSize, fallDownCount)) {
            pins.remove(PINS_MIN_COUNT);
        }
        return this;
    }

    private boolean falling(int startSize, int fallDownCount) {
        return size() != (startSize - fallDownCount);
    }

    public boolean isStrike() {
        return size() == PINS_MAX_COUNT;
    }

    public boolean isStrike(Pins fallDownPins) {
        return isStrike() && fallDownPins.size() == PINS_MAX_COUNT;
    }

    public boolean isSpare(Pins secondPins) {
        return size() + secondPins.size() == PINS_MAX_COUNT;
    }

    public boolean isMiss() {
        return size() < PINS_MAX_COUNT;
    }

    public boolean isMiss(Pins secondPins) {
        return size() + secondPins.size() < PINS_MAX_COUNT;
    }

    public boolean isGutter() {
        return size() == PINS_MIN_COUNT;
    }

    public int size() {
        return pins.size();
    }

    public boolean isEmpty() {
        return size() == PINS_MIN_COUNT;
    }

    private void validateFallDownCount(int fallDownCount) {
        if (pins.size() < fallDownCount) {
            throw new IllegalArgumentException("남은 핀 개수 보다 많이 쓰러뜨릴 수 없습니다.");
        }
    }

    private void validatePinsSize() {
        if (pins.size() > PINS_MAX_COUNT) {
            throw new IllegalArgumentException("핀들이 제대로 생성되지 않았습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }

    @Override
    public String toString() {
        return "Pins{" +
                "pins=" + pins +
                '}';
    }
}
