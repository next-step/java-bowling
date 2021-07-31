package bowling.domain.pin;

public class DownedPin {
    private final int numOfDownedPins;

    private DownedPin(int numOfDownedPins) {
        this.numOfDownedPins = numOfDownedPins;
    }

    public static DownedPin from(int numOfDownedPins) {
        return new DownedPin(numOfDownedPins);
    }
}
