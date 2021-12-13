package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final int PIN_MIN_COUNT = 1;
    private static final int PIN_MAX_COUNT = 10;

    private final List<Pin> pins = new ArrayList<>();

    private Pins() {
        init(PIN_MAX_COUNT);
        validatePinsSize();
    }

    private Pins(int count) {
        init(count);
        validatePinsSize();
    }

    private void init(int count) {
        for (int number = PIN_MIN_COUNT; number <= count; number++) {
            pins.add(Pin.from(number));
        }
    }

    public static Pins create() {
        return new Pins();
    }

    public static Pins create(int count) {
        return new Pins(count);
    }

    public List<Pin> pins() {
        return pins;
    }

    public int size() {
        return pins.size();
    }

    private void validatePinsSize() {
        if (pins.size() != PIN_MAX_COUNT) {
            throw new IllegalArgumentException("핀들이 제대로 생성되지 않았습니다.");
        }
    }
}
