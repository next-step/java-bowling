package bowling.domain.state.progress;

import bowling.Pin;
import bowling.domain.state.State;

public abstract class Progress implements State {

    protected final Pin hitPin;

    protected Progress(Pin hitPin) {
        this.hitPin = hitPin;
    }

    public Pin getHitPin() {
        return hitPin;
    }

    public int getHitCount() {
        return this.hitPin.getHitCount();
    }
}
