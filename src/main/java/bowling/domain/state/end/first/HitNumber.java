package bowling.domain.state.end.first;

import bowling.domain.Pin;
import bowling.domain.state.end.ResultState;
import bowling.domain.state.end.PinResultState;

public class HitNumber extends PinResultState implements NextAbleState {

    public HitNumber(Pin pin) {
        super(pin);
    }

    @Override
    public String getPrintMark() {
        return String.valueOf(pin.getHitCount());
    }

    @Override
    public boolean isInstanceOf(Class<? extends ResultState> clazz) {
        return clazz.isInstance(this);
    }

    public boolean isSpare(Pin pin) {
        return this.pin.isSpare(pin);
    }

}
