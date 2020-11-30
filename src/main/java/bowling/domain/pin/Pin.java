package bowling.domain.pin;

public class Pin {
    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;

    private final int pins;

    private Pin(int pins) {
        this.pins = pins;
    }

    public static Pin of(int pins) {
        validPins(pins);
        return new Pin(pins);
    }

    private static void validPins(int pins) {
        validMax(pins);
        validMin(pins);
    }

    private static void validMax(int pins) {
        if (pins > MAX_PINS) {
            throw new InvalidMaximumPinException(pins);
        }
    }

    private static void validMin(int pins) {
        if (pins < MIN_PINS) {
            throw new InvalidMinimumPinException(pins);
        }
    }

    public int getPins() {
        return pins;
    }

    public boolean isGutter() {
        return pins == MIN_PINS;
    }

    public boolean isStrike() {
        return pins == MAX_PINS;
    }

    public boolean isSpare(int nextPins) {
        return pins + nextPins == MAX_PINS;
    }
}

