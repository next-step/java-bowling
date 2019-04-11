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
        return first.isZeroPin() ? ZERO_PIN_DISPLAY_STRING : first.toString();
    }

    @Override
    public Status getNext(Pin pin) {
        if (first.isSpare(pin)) {
            return (next = new Spare(first, pin));
        }

        return (next = new Open(first, pin));
    }
}