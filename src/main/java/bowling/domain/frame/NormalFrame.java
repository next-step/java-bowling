package bowling.domain.frame;

import bowling.domain.Pins;

public class NormalFrame {
    private static final int FIRST_FRAME = 1;

    private Pins pins;
    private int index;

    private NormalFrame(final int index) {
        this.pins = new Pins();
        this.index = index;
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_FRAME);
    }

    public NormalFrame nextFrame() {
        return new NormalFrame(index + 1);
    }

    public void roll(int pins) {
        this.pins.roll(pins);
    }

    public int getPins() {
        return this.pins.getTotalPins();
    }

    public int getIndex() {
        return index;
    }

}
