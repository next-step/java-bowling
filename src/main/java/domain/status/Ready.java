package domain.status;

import domain.pin.Pin;

public class Ready extends Status {
    public Ready() {
        super();
    }

    @Override
    public boolean isClear() {
        return false;
    }

    @Override
    public boolean isNormalFrameFinished() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Status getNext(Pin pin) {
        return super.getNext(pin);
    }
}