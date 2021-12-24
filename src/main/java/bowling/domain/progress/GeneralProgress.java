package bowling.domain.progress;

import bowling.Pin;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.HitNumber;

public class GeneralProgress implements Progress, Opened {

    private final EndState beforeState;

    public GeneralProgress(EndState beforeState) {
        this.beforeState = beforeState;
    }

    @Override
    public EndState pitch(Pin pin) {
        if (pin.isStrike()) {
            if (beforeState.isInstanceOf(Strike.class)) {
                return StateFactory.strike();
            }

            return StateFactory.spare();
        }

        if (pin.isGutter()) {
            return StateFactory.gutter();
        }

        if (beforeState.isInstanceOf(HitNumber.class)) {
            if (isSpare(pin, (HitNumber) this.beforeState)) {
                return StateFactory.spare();
            }
        }

        return StateFactory.number(pin);
    }

    private boolean isSpare(Pin pin, HitNumber beforeHitNumber) {
        return beforeHitNumber.isSpare(pin);
    }

}
