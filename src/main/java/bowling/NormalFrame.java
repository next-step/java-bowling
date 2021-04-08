package bowling;

public class NormalFrame {

    private boolean finish;
    private PinNumber first;
    private PinNumber second;

    public NormalFrame() {
        finish = false;
    }

    public boolean first(PinNumber first) {
        if (first.isStrike()) {
            finish = true;
        }
        this.first = first;
        return finish;
    }

    public void second(PinNumber second) {
        if (first.getPinNumber() + second.getPinNumber() > 10) {
            throw new IllegalArgumentException();
        }
        this.second = second;
    }

    public void eachState() {
        FrameState.eachState(first);
    }

    public void finishState() {
        FrameState.finishState(first, second);
    }
}
