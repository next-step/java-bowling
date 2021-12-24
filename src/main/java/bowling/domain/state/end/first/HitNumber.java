package bowling.domain.state.end.first;

import bowling.Pin;
import bowling.domain.state.end.EndState;

public class HitNumber implements NextAbleState, EndState {

    private final Pin pin;

    public HitNumber(Pin pin) {
        this.pin = pin;
    }

    @Override
    public String getPrintMark() {
        return String.valueOf(pin.getHitCount());
    }

    @Override
    public boolean isInstanceOf(Class<? extends EndState> clazz) {
        return clazz.isInstance(this);
    }

    public boolean isSpare(Pin pin) {
        return this.pin.isSpare(pin);
    }

    public Pin getHitPin() {
        return pin;
    }
}
