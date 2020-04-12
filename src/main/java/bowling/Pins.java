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
        changePins(dropPinCount);
        return dropPinCount;
    }

    private void changePins(final int dropPinCount) {
        if (pinCount < dropPinCount) {
            throw new RuntimeException("Remain pin count must be greater than dropping pin count.");
        }

        pinCount -= dropPinCount;
    }

    public void reset() {
        this.pinCount = MAX_PIN_COUNT;
    }
}
