package bowling.model.frame;

import java.util.HashMap;
import java.util.Map;

public class FallenPin {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final String STRIKE_SYMBOL = "X";
    private static final String GUTTER_SYMBOL = "-";
    private static final Map<Integer, FallenPin> FALLEN_PINS = new HashMap<>();

    private final int fallenCount;

    private FallenPin(int fallenCount) {
        validateRange(fallenCount);

        this.fallenCount = fallenCount;
    }

    public static FallenPin of(int fallenCount) {
        FallenPin fallenPin = FALLEN_PINS.get(fallenCount);
        if (fallenPin != null) {
            return fallenPin;
        }

        fallenPin = new FallenPin(fallenCount);
        FALLEN_PINS.put(fallenCount, fallenPin);
        return fallenPin;
    }

    private void validateRange(int fallenCount) {
        if (fallenCount < MIN || fallenCount > MAX) {
            throw new IllegalArgumentException("쓰러진 핀은 0개 이상 10개 이하여야 합니다.");
        }
    }

    public int count() {
        return fallenCount;
    }

    public boolean isMax() {
        return fallenCount == MAX;
    }

    public boolean isMin() {
        return fallenCount == MIN;
    }

    @Override
    public String toString() {
        if (isMin()) {
            return GUTTER_SYMBOL;
        }

        if (isMax()) {
            return STRIKE_SYMBOL;
        }
        return String.valueOf(fallenCount);
    }
}
