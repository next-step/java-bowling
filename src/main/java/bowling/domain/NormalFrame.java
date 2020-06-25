package bowling.domain;

public class NormalFrame {
    private static final int FIRST_FRAME = 1;
    private Pin pin;
    private int index;

    private NormalFrame(final int index) {
        this.pin = new Pin();
        this.index = index;
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    public NormalFrame nextFrame() {
        return new NormalFrame(index + 1);
    }

    public void addPins(int pin) {
        this.pin.addPins(pin);
    }

    public int getIndex() {
        return index;
    }

    public int getPin() {
        return pin.getPin();
    }
}
