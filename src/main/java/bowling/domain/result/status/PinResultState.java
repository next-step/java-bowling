package bowling.domain.result.status;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

public abstract class PinResultState implements ResultState {

    protected final Pin pin;

    protected PinResultState(Pin pin) {
        this.pin = pin;
    }

    public final int getHitPinCount() {
        return this.pin.getHitCount();
    }

}
