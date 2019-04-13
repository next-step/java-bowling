package domain.status;

import domain.pin.Pin;

public class Ready extends Status {
    private Status previous;

    public Ready() {
        super();
    }

    public Ready(Status previous) {
        this.previous = previous;
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
        Status nextStatus = super.getNext(pin);

        if (previous != null) {
            previous.next = nextStatus;
        }

        return nextStatus;
    }
}