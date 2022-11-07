package bowling.domain.status;

import bowling.domain.Pin;

public abstract class Status {
    public abstract Status bowl(Pin pin);
    public abstract boolean isFinished();
}
