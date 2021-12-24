package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.ResultState;

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
