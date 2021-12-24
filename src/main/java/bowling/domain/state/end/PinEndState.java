package bowling.domain.state.end;

import bowling.domain.Pin;

public abstract class PinEndState implements EndState {

    protected final Pin pin;

    protected PinEndState(Pin pin) {
        this.pin = pin;
    }

    public int getHitPinCount() {
        return this.pin.getHitCount();
    }

}
