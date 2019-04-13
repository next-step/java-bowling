package domain.status;

import domain.pin.Pin;
import domain.score.CannotCalculateException;
import domain.score.Score;

public abstract class Status {

    protected Pin first;
    protected Status next;

    public Status() {
    }

    public Status(Pin first) {
        this.first = first;
    }

    public int getCurrentPin() {
        return first.getPin();
    }

    public Status getNext() {
        return next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Status getNext(Pin pin) {
        if (pin.isStrike()) {
            return (next = new Strike(pin));
        }

        return (next = new FirstBowlFinished(pin));
    }

    public abstract boolean isClear();

    public abstract boolean isNormalFrameFinished();

    public abstract String toString();

    public Score getScore() {
        throw new CannotCalculateException();
    }
}