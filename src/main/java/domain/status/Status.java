package domain.status;

import domain.pin.Pin;

public abstract class Status {
    static final int DEFAULT_BONUS_COUNT = 0;

    protected Pin pin;

    public Status() {
    }

    public Status(Pin pin) {
        this.pin = pin;
    }

    public int getBonusCount() {
        return DEFAULT_BONUS_COUNT;
    }

    public Status getNext(Pin pin) {
        if (pin.isStrike()) {
            return new Strike();
        }

        return new FirstBowlFinished(pin);
    }

    public abstract boolean isClear();
    public abstract boolean isNormalFrameFinished();
    public abstract String toString();
}