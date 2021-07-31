package bowling.domain.pin;

public class Pin {
    private final int downedPins;

    private Pin(int downedPins) {
        this.downedPins = downedPins;
    }

    public static Pin from(int downedPins) {
        return new Pin(downedPins);
    }
}
