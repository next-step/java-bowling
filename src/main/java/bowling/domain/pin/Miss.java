package bowling.domain.pin;

public class Miss extends FramePins {
    public Miss(Pins firstPins, Pins secondPins) {
        super(firstPins, secondPins);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    public static Miss of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }
}
