package bowling.domain.pin;

public class FirstFramePins extends FramePins {
    private FirstFramePins(final Pins firstPins) {
        super(firstPins, Pins.of(0));
    }

    public static FirstFramePins of(Pins pins) {
        return new FirstFramePins(pins);
    }

    @Override
    public String toString() {
        return super.firstPins.toString();
    }
}
