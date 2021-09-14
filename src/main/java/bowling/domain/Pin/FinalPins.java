package bowling.domain.Pin;

public class FinalPins {

    private final Pin first;
    private final Pin second;

    private FinalPins(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static FinalPins ofFirst(Pin pin) {
        return new FinalPins(pin, null);
    }

}
