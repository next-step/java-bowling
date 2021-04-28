package bowling.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(pinNumbers, frame.pinNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinNumbers);
    }
}
