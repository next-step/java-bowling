package domain.status;

import domain.pin.Pin;

public class FirstBowlFinished extends Status {
    static final String ZERO_PIN_DISPLAY_STRING = "-";

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
        return pin.isZeroPin() ? ZERO_PIN_DISPLAY_STRING : pin.toString();
    }

    @Override
    public Status getNext(Pin pin) {
        if(this.pin.isSpare(pin)) {
            return new Spare();
        }

        return new Open(pin);
    }
}