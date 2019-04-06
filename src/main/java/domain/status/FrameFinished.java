package domain.status;

import domain.pin.Pin;

public abstract class FrameFinished extends Status {
    public FrameFinished() {
        super();
    }

    public FrameFinished(Pin pin) {
        super(pin);
    }

    @Override
    public boolean isNormalFrameFinished() {
        return true;
    }

    @Override
    public Status getNext(Pin pin) {
        return super.getNext(pin);
    }
}