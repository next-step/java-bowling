package bowling.domain.frame;

public class FrameProperties {
    private final int index;
    private final Pins pins;

    private int fallenPins;
    private int tryNo;

    public FrameProperties(int index) {
        this(index, new Pins(), 0, 2);
    }

    public FrameProperties(int index, Pins pins, int fallenPins, int tryNo) {
        this.index = index;
        this.pins = pins;
        this.fallenPins = fallenPins;
        this.tryNo = tryNo;
    }

    public int index() {
        return index;
    }
}
