package bowling.domain.pin;

public class Miss extends FramePins {
    public Miss(Pins firstPins, Pins secondPins) {
        super(firstPins, secondPins);
    }

    public static Miss of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
