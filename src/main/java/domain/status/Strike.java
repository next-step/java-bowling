package domain.status;

import domain.pin.Pin;

public class Strike extends FrameFinished {
    static final String STRIKE_DISPLAY_STRING = "X";

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
        return STRIKE_DISPLAY_STRING;
    }
}