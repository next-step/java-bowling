package bowling;

public class LastFrame {
    private final Pins fallenPins;

    public LastFrame() {
        this.fallenPins = new Pins();
    }

    public LastFrame bowl(int fallenPinCount) {
        fallenPins.bowl(fallenPinCount);
        return this;
    }

    public boolean isGameEnd() {
        return fallenPins.isFinish();
    }
}
