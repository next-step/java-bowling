package bowling.domain.pin;

public class Gutter extends FramePins {
    public Gutter(Pins firstPins, Pins secondPins) {
        super(firstPins, secondPins);
    }

    public static Gutter of() {
        return new Gutter(Pins.of(0), Pins.of(0));
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
