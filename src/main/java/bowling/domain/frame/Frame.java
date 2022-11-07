package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.status.Ready;
import bowling.domain.status.Status;

public abstract class Frame {

    protected int frameNo;
    protected Status status = new Ready();

    Frame bowl(Pin pin) {
        return null;
    }

    Status getStatus() {
        return status;
    }

    public abstract Boolean isFinished();

    public abstract Boolean isFinalFrame();

    public abstract Frame nextFrame();
}
