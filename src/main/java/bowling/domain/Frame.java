package bowling.domain;

abstract class Frame implements FrameStrategy {
    protected final PinNumbers pinNumbers;

    protected Frame() {
        this.pinNumbers = new PinNumbers();
    }

    public int size() {
        return pinNumbers.size();
    }

    public String state(int index) {
        return pinNumbers.state(index);
    }

    public void play(PinNumber pinNumber) {
        pinNumbers.record(pinNumber);
    }
}
