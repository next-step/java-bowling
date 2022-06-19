package bowling.domain.frame;

public class FinalFrame implements Frame {

    private final int index;
    private final Pins pins;

    private int tryNo;
    private int fallenPins;
    private int bonus = 1;

    public FinalFrame(int index) {
        this(index, 2, 0, new Pins());
    }

    public FinalFrame(int index, int tryNo, int fallenPins, Pins pins) {
        this.index = index;
        this.tryNo = tryNo;
        this.fallenPins = fallenPins;
        this.pins = pins;
    }

    @Override
    public int determineSpare(int fallenPins) {
        this.fallenPins += fallenPins;
        tryNo--;
        if (isBonus()) {
            tryNo += bonus--;
            this.fallenPins = 0;
        }
        pins.add(fallenPins);
        return 0;
    }

    private boolean isBonus() {
        return tryNo < 2 && fallenPins == 10;
    }

    @Override
    public Pins pins() {
        return pins;
    }

    @Override
    public int validateMoveToNextIndex() {
        if (moveable()) { // 다 던지면 끝
            return index + 1;
        }
        return index;
    }

    private boolean moveable() {
        return tryNo < 1;
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
        return "FinalFrame{" +
                "index=" + index +
                ", pins=" + pins +
                '}';
    }
}
