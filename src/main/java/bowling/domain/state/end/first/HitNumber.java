package bowling.domain.state.end.first;

import bowling.Pin;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.PinEndState;

public class HitNumber extends PinEndState implements NextAbleState {

    public HitNumber(Pin pin) {
        super(pin);
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

}
