package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.ResultState;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.HitNumber;

public class GeneralProgress implements Progress, Opened {

    private final ResultState beforeState;

    public GeneralProgress(ResultState beforeState) {
        this.beforeState = beforeState;
    }

    @Override
    public ResultState pitch(Pin pin) {
        if (pin.isStrike()) {
            if (beforeState.isInstanceOf(Strike.class)) {
                return StateFactory.strike(pin);
            }

            return StateFactory.spare(pin);
        }

        if (pin.isGutter()) {
            return StateFactory.gutter(pin);
        }

        if (beforeState.isInstanceOf(HitNumber.class)) {
            if (isSpare(pin, (HitNumber) this.beforeState)) {
                return StateFactory.spare(pin);
            }
        }

        return StateFactory.number(pin);
    }

    private boolean isSpare(Pin pin, HitNumber beforeHitNumber) {
        return beforeHitNumber.isSpare(pin);
    }

}
