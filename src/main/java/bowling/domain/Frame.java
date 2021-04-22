package bowling.domain;

abstract class Frame implements FrameStrategy {
    protected PinNumbers pinNumbers;
    protected Frame next;

    public Frame() {
        this.pinNumbers = new PinNumbers();
        this.next = null;
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
