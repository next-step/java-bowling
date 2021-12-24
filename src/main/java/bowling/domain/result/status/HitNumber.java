package bowling.domain.result.status;

import bowling.domain.Pin;
import bowling.domain.result.ResultState;

public class HitNumber extends PinResultState implements NextAbleState {

    public HitNumber(Pin pin) {
        super(pin);

        valid(pin);
    }

    private void valid(Pin pin) {
        if (pin.isStrike() || pin.isGutter()) {
            throw new IllegalArgumentException("HitNumber이 아닌, 다른 값이 와야 해요.");
        }

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
