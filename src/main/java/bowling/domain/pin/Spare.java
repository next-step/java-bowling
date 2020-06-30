package bowling.domain.pin;

public class Spare extends FramePins {
    private Spare(Pins firstPins, Pins secondPins) {
        super(firstPins, secondPins);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    public static Spare of(Pins firstPins, Pins secondPins) {
        validate(firstPins, secondPins);
        return new Spare(firstPins, secondPins);
    }

    private static void validate(Pins firstPins, Pins secondPins) {
        if (Pins.sum(firstPins, secondPins) != MAX_PINS_PER_FRAME) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return super.firstPins.toString() + "|/";
    }
}
