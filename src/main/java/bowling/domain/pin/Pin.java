package bowling.domain.pin;

public class Pin {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int falledPins;

    private Pin(int falledPins) {
        this.falledPins = falledPins;
    }

    public static Pin of(int falledPins) {
        validPins(falledPins);
        return new Pin(falledPins);
    }

    private static void validPins(int falledPins) {
        validMax(falledPins);
        validMin(falledPins);
    }

    private static void validMax(int falledPins) {
        if (falledPins > MAX_PINS) {
            throw new InvalidMaximumPinException(falledPins);
        }
    }

    private static void validMin(int falledPins) {
        if (falledPins < MIN_PINS) {
            throw new InvalidMinimumPinException(falledPins);
        }
    }

    public int getFalledPins() {
        return falledPins;
    }
}

