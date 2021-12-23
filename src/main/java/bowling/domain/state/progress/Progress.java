package bowling.domain.state.progress;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import bowling.domain.state.UpdateAbleState;

public abstract class Progress implements State, UpdateAbleState {

    protected final Frame frame;
    protected final Pin hitPin;

    protected Progress(Frame frame, Pin hitPin) {
        this.frame = frame;
        this.hitPin = hitPin;
    }

    public Pin getHitPin() {
        return hitPin;
    }

    public int getHitCount() {
        return this.hitPin.getHitCount();
    }
}
