package bowling.domain;

public class Frame {

    public static final int PIN_COUNT = 10;
    private int pinCount;

    private Frame(int pinCount) {
        this.pinCount = pinCount;
    }

    public static Frame create() {
        return new Frame(PIN_COUNT);
    }

    public int getPinCount() {
        return pinCount;
    }
}
