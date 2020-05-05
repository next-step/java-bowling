package bowling;

public class NormalFrame implements Frame {
    private final Pins fallenPins;
    private int number;

    public NormalFrame(int number) {
        this.number = number;
        this.fallenPins = new Pins();
    }

    @Override
    public NormalFrame bowl(int fallenPinCount) {
        fallenPins.bowl(fallenPinCount);
        if (fallenPins.isStrike()) {
            return new NormalFrame(number + 1);
        }
        if (fallenPins.isFinish()) {
            return new NormalFrame(number + 1);
        }

        return this;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public String getRecord() {
        return fallenPins.getDescription();
    }
}
