package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.result.StateFactory;
import bowling.domain.result.ResultState;
import bowling.domain.result.status.PinResultState;
import bowling.domain.result.status.Strike;
import bowling.domain.result.status.HitNumber;

public class GeneralProgress implements Progress, Opened {

    private final ResultState beforeState;

    public GeneralProgress(ResultState beforeState) {
        this.beforeState = beforeState;
    }

    @Override
    public PinResultState pitch(Pin pin) {
        if (pin.isStrike()) {
            return resultByBeforeState(pin);
        }

        if (pin.isGutter()) {
            return StateFactory.gutter(pin);
        }

        if (beforeState.isInstanceOf(HitNumber.class) && isSpare(pin, (HitNumber) this.beforeState)) {
            return StateFactory.spare(pin);
        }

        return StateFactory.number(pin);
    }

    private PinResultState resultByBeforeState(Pin pin) {
        if (beforeState.isInstanceOf(Strike.class)) {
            return StateFactory.strike(pin);
        }

        return StateFactory.spare(pin);
    }

    private boolean isSpare(Pin pin, HitNumber beforeHitNumber) {
        return beforeHitNumber.isSpare(pin);
    }

}
