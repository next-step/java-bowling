package bowling.domain.pin;

public class Pin {
    public static final int MIN_FELL_PINS = 0;
    public static final int MAX_FELL_PINS = 10;

    private final int fellPins;

    private Pin(int fellPins) {
        this.fellPins = fellPins;
    }

    public static Pin of(int fellPins) {
        validPins(fellPins);
        return new Pin(fellPins);
    }

    private static void validPins(int fellPins) {
        validMax(fellPins);
        validMin(fellPins);
    }

    private static void validMax(int fellPins) {
        if (fellPins > MAX_FELL_PINS) {
            throw new InvalidMaximumPinException(fellPins);
        }
    }

    private static void validMin(int fellPins) {
        if (fellPins < MIN_FELL_PINS) {
            throw new InvalidMinimumPinException(fellPins);
        }
    }

    public int getFellPins() {
        return fellPins;
    }

    public boolean isNoneFell() {
        return fellPins == MIN_FELL_PINS;
    }

    public boolean isAllFell() {
        return fellPins == MAX_FELL_PINS;
    }

    public boolean isRestFell(int nextPins) {
        return fellPins + nextPins == MAX_FELL_PINS;
    }
}

