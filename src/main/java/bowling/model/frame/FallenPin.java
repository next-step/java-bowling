package bowling.model.frame;

import java.util.HashMap;
import java.util.Map;

public class FallenPin {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final String STRIKE_SYMBOL = "X";
    private static final String GUTTER_SYMBOL = "-";
    private static final Map<Integer, FallenPin> FALLEN_PINS = new HashMap<>();

    private final int count;

    private FallenPin(int fallenPinCount) {
        validateRange(fallenPinCount);

        this.count = fallenPinCount;
    }

    public static FallenPin of(int fallenPinCount) {
        FallenPin fallenPin = FALLEN_PINS.get(fallenPinCount);
        if (fallenPin != null) {
            return fallenPin;
        }

        fallenPin = new FallenPin(fallenPinCount);
        FALLEN_PINS.put(fallenPinCount, fallenPin);
        return fallenPin;
    }

    private void validateRange(int fallenCount) {
        if (fallenCount < MIN || fallenCount > MAX) {
            throw new IllegalArgumentException("쓰러진 핀은 0개 이상 10개 이하여야 합니다.");
        }
    }

    public int count() {
        return count;
    }

    public boolean isMax() {
        return count == MAX;
    }

    public boolean isMin() {
        return count == MIN;
    }

    @Override
    public String toString() {
        if (isMin()) {
            return GUTTER_SYMBOL;
        }

        if (isMax()) {
            return STRIKE_SYMBOL;
        }
        return String.valueOf(count);
    }
}
