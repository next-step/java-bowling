package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.result.StateFactory;
import bowling.domain.result.ResultState;

public class FirstProgress implements Progress, Opened {

    @Override
    public ResultState pitch(Pin pin) {
        if (pin.isStrike()) {
            return StateFactory.strike(pin);
        }

        if (pin.isGutter()) {
            return StateFactory.gutter(pin);
        }

        return StateFactory.number(pin);
    }
}
