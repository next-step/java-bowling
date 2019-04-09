package domain.status;

import domain.pin.Pin;

public abstract class Status {
    protected Pin pin;

    public Status() {
    }

    public Status(Pin pin) {
        this.pin = pin;
    }

    public abstract boolean isClear();
    public abstract boolean isNormalFrameFinished();
    public abstract String toString();

    public Status getNext(Pin pin) {
        if (pin.isStrike()) {
            return new Strike();
        }

        return new FirstBowlFinished(pin);
    }
}