package bowling;

public class Pins {

    public static final int MAX_PIN_COUNT = 10;

    private int pinCount;

    public Pins() {
        this.pinCount = MAX_PIN_COUNT;
    }

    public boolean isRemain(final int count) {
        return pinCount == count;
    }

    public int drop(final int dropPinCount) {
        return changePins(dropPinCount);
    }

    private int changePins(final int dropPinCount) {
        if (pinCount < dropPinCount) {
            throw new RuntimeException("Remain pin count must be greater than dropping pin count.");
        }

        pinCount -= dropPinCount;
        return pinCount;
    }

    public void reset() {
        this.pinCount = MAX_PIN_COUNT;
    }
}
