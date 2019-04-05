package domain.status;

import static domain.pin.Pin.MAXIMUM_PINS;
import static domain.pin.Pin.MINIMUM_PINS;

public class Ready extends Status {

    public Ready(int pin) {
        super(pin);
    }

    @Override
    public boolean isFrameFinished() {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public Status getNext(int pin) {
        if(pin == MAXIMUM_PINS) {
            return new Strike(pin);
        }

        if(pin == MINIMUM_PINS) {
            return new Gutter(pin);
        }

        return new FirstBowlFinished(pin);
    }
}