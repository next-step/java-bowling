package bowling;

public class NormalFrame implements Frame {
    private static final int LAST_FRAME_NUMBER = 10;

    private final Pins fallenPins;
    private int number;

    public NormalFrame(int number) {
        this.number = number;
        this.fallenPins = new Pins();
    }

    @Override
    public Frame bowl(int fallenPinCount) {
        fallenPins.bowl(fallenPinCount);
        if (fallenPins.isFinish()) {
            return createNextFrame();
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

    private Frame createNextFrame() {
        if (number + 1 == LAST_FRAME_NUMBER) {
            return new LastFrame();
        }
        return new NormalFrame(number + 1);
    }
}
