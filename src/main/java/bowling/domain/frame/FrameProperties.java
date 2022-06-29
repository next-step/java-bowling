package bowling.domain.frame;

import java.util.Objects;

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

    public void plusTryNo(int tryNo) {
        this.tryNo += tryNo;
    }

    @Override
    public String toString() {
        return "FrameProperties{" +
                "index=" + index +
                ", fallenPins=" + fallenPins +
                ", tryNo=" + tryNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameProperties that = (FrameProperties) o;
        return index == that.index && tryNo == that.tryNo && fallenPins.equals(that.fallenPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, fallenPins, tryNo);
    }
}
