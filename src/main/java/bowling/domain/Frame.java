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

    public String result(int index) {
        return pinNumbers.result(index);
    }

    public void play(PinNumber pinNumber) {
        pinNumbers.record(pinNumber);
    }

    public int sum() {
        return pinNumbers.sum();
    }

    public int firstScore() {
        return pinNumbers.index(0).pinNumber();
    }

    public int secondScore() {
        return pinNumbers.index(0).pinNumber();
    }
}
