package domain.status;

import domain.pin.Pin;

public abstract class FrameFinished extends Status {
    public FrameFinished() {
        super();
    }

    public FrameFinished(Pin first) {
        super(first);
    }

    @Override
    public boolean isNormalFrameFinished() {
        return true;
    }

    @Override
    public Status getNext(Pin pin) {
        return (next = super.getNext(pin));
    }
}