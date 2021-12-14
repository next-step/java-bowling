package bowling.domain.frame;

import java.util.Objects;

public class Pin {

    private static final String NO_RANGE_EXCEPTION_MESSAGE_FORMAT = "핀의 갯수는 %d ~ %d 사이여야 합니다.";
    private static final String GUTTER_STRING = "-";
    private static final String STRIKE_STRING = "X";
    private static final int GUTTER_PIN_COUNT = 0;
    private static final int STRIKE_PIN_COUNT = 10;
    private static final Pin[] zeroThroughTen = {
            new Pin(0),
            new Pin(1),
            new Pin(2),
            new Pin(3),
            new Pin(4),
            new Pin(5),
            new Pin(6),
            new Pin(7),
            new Pin(8),
            new Pin(9),
            new Pin(10)
    };

    private static final int MIN = 0;
    private static final int MAX = 10;
    public static final Pin TEN = zeroThroughTen[MAX];

    private final int falledPins;

    private Pin(int falledPins) {
        this.falledPins = falledPins;
    }

    private static void validateCountWithRange(int falledPins) {
        if (falledPins < MIN || falledPins > MAX) {
            throw new IllegalArgumentException(String.format(NO_RANGE_EXCEPTION_MESSAGE_FORMAT, MIN, MAX));
        }
    }

    public static Pin from(int falledPins) {
        validateCountWithRange(falledPins);
        return zeroThroughTen[falledPins];
    }

    public Pin add(Pin other) {
        return Pin.from(this.falledPins + other.falledPins);
    }

    public boolean isMaxCount() {
        return this.falledPins == MAX;
    }

    public String viewString() {
        if (falledPins == GUTTER_PIN_COUNT) {
            return GUTTER_STRING;
        }

        if (falledPins == STRIKE_PIN_COUNT) {
            return STRIKE_STRING;
        }

        return String.valueOf(falledPins);
    }

    public int getFalledPins() {
        return falledPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pin pin = (Pin) o;
        return falledPins == pin.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
