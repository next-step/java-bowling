package bowling;

public class Frame {
    private final Pins fallenPins;
    private int number;

    public Frame(int number) {
        this.number = number;
        this.fallenPins = new Pins();
    }

    public Frame bowl(int fallenPinCount) {
        fallenPins.bowl(fallenPinCount);
        if (fallenPins.isStrike()) {
            return new Frame(number + 1);
        }
        if (fallenPins.isFinish()) {
            return new Frame(number + 1);
        }

        return this;
    }

    public int getNumber() {
        return this.number;
    }

    public String getRecord() {
        return fallenPins.getDescription();
    }
}
