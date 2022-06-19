package bowling.domain.frame;

public class NormalFrame implements Frame {
    private final int index;
    private final Pins pins;

    private int fallenPins;
    private int tryNo;

    public NormalFrame(int index) {
        this(index, 2, new Pins());
    }

    public NormalFrame(int index, int tryNo, Pins pins) {
        this.index = index;
        this.tryNo = tryNo;
        this.pins = pins;
    }

    public void addPins(int pins) {
        this.pins.add(pins);
    }

    @Override
    public Pins pins() {
        return pins;
    }

    @Override
    public int determineSpare(int fallenPins) {
        if (10 < this.fallenPins + fallenPins) {
            // throw "쓰러트린 핀의 개수가 10가 넘습니다."
        }
        this.fallenPins += fallenPins;
        tryNo--;
        pins.add(fallenPins);
        return 10 - this.fallenPins;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) {
            return index + 1;
        }
        return index;
    }

    private boolean moveable() {
        return tryNo < 1 || fallenPins > 9;
    }

    @Override
    public boolean equal(int index) {
        return this.index == index;
    }

    @Override
    public int index() {
        return index;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pins=" + pins +
                '}';
    }
}
