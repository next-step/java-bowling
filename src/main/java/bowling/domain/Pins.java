package bowling.domain;

public class Pins {
    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;


    private final int falledPins;

    private Pins(int falledPins) {
        this.falledPins = falledPins;
    }

    public static Pins pitching(int falledPins) {
        validPins(falledPins);
        return new Pins(falledPins);
    }

    private static void validPins(int falledPins) {
        if (falledPins < MIN_PIN) {
            throw new CannotBeLessThanZero(falledPins);
        }

        if (falledPins > MAX_PIN) {
            throw new CannotBeBiggerThanMax(falledPins);
        }
    }

}
