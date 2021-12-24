package bowling.domain.progress;

import bowling.domain.Pin;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.EndState;

public class FirstProgress implements Progress, Opened {

    @Override
    public EndState pitch(Pin pin) {
        if (pin.isStrike()) {
            return StateFactory.strike(pin);
        }

        if (pin.isGutter()) {
            return StateFactory.gutter(pin);
        }

        return StateFactory.number(pin);
    }
}
