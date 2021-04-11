package bowling.domain;

import java.util.List;

abstract class Frame implements FrameStrategy {
    protected final PinNumbers pinNumbers;

    protected Frame() {
        this.pinNumbers = new PinNumbers();
    }

    public List<PinNumber> getPinNumbers() {
        return pinNumbers.getPinNumbers();
    }

    public String state(int index) {
        return pinNumbers.state(index);
    }

    public void play(PinNumber pinNumber) {
        pinNumbers.record(pinNumber);
    }
}
