package bowling.domain.frame;

public class FrameProperties {
    private final int index;
    private final FallenPins fallenPins;
    private int tryNo;

    public FrameProperties(int index) {
        this(index, new FallenPins(), 2);
    }

    public FrameProperties(int index, FallenPins fallenPins, int tryNo) {
        this.index = index;
        this.fallenPins = fallenPins;
        this.tryNo = tryNo;
    }

    public int index() {
        return index;
    }

    public void addPins(int fallenPins) {
        this.fallenPins.add(fallenPins);
    }

    public FallenPins pins() {
        return fallenPins;
    }

    public int computeSumOfFallenPins() {
        return pins().computeSum();
    }

    public void minusTryNo() {
        tryNo--;
    }

    public int tryNo() {
        return tryNo;
    }

    @Override
    public String toString() {
        return "FrameProperties{" +
                "index=" + index +
                ", fallenPins=" + fallenPins +
                ", tryNo=" + tryNo +
                '}';
    }
}
