package domain.status;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;

public class FirstBowlFinished extends Status {

    protected FirstBowlFinished(int pin) {
        super(pin);
    }

    @Override
    public boolean isFrameFinished() {
        return false;
    }

    @Override
    public String toString() {
        return pin + "|";
    }

    @Override
    public Status getNext(int pin) {
        if(this.pin + pin == MINIMUM_PINS) {
            return new Miss(pin);
        }

        if(this.pin + pin == MAXIMUM_PINS) {
            return new Spare(pin);
        }

        return new Open(pin);
    }
}