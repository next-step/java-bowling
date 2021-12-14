package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Pins {
    private static final int PIN_MIN_COUNT = 1;
    private static final int PIN_MAX_COUNT = 10;
    private static final int FIRST_PIN_INDEX = 0;

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

    public int fallDown(int fallDownCount) {
        validateFallDownCount(fallDownCount);
        int startSize = size();
        while (falling(startSize, fallDownCount)) {
            pins.remove(FIRST_PIN_INDEX);
        }
        return size();
    }

    private boolean falling(int startSize, int fallDownCount) {
        return size() != (startSize - fallDownCount);
    }


    public List<Pin> pins() {
        return pins;
    }

    public int size() {
        return pins.size();
    }

    private void validateFallDownCount(int fallDownCount) {
        if (pins.size() < fallDownCount) {
            throw new IllegalArgumentException("남은 핀 개수 보다 많이 쓰러뜨릴 수 없습니다.");
        }
    }

    private void validatePinsSize() {
        if (pins.size() != PIN_MAX_COUNT) {
            throw new IllegalArgumentException("핀들이 제대로 생성되지 않았습니다.");
        }
    }
}
