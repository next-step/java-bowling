package domain.status;

import domain.pin.Pin;

public class FirstBowlFinished extends Status {
    public FirstBowlFinished(Pin pin) {
        super(pin);
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
        return pin.isZeroPin() ? "-" : pin.toString();
    }

    @Override
    public Status getNext(Pin pin) {
        if(this.pin.isSpare(pin)) {
            return new Spare();
        }

        return new Open(pin);
    }
}