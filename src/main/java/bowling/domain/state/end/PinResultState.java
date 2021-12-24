package bowling.domain.state.end;

import bowling.domain.Pin;

public abstract class PinResultState implements ResultState {

    protected final Pin pin;

    protected PinResultState(Pin pin) {
        this.pin = pin;
    }

    public int getHitPinCount() {
        return this.pin.getHitCount();
    }

}
