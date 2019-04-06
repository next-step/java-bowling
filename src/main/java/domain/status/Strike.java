package domain.status;

import domain.pin.Pin;

public class Strike extends FrameFinished {
    @Override
    public Status getNext(Pin pin) {
        return super.getNext(pin);
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
        return "X";
    }
}