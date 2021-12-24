package bowling.domain.progress;

import bowling.Pin;
import bowling.domain.state.StateFactory;
import bowling.domain.state.end.EndState;

public class FirstProgress implements Progress, Opened {

    @Override
    public EndState pitch(Pin pin) {
        if (pin.isStrike()) {
            return StateFactory.strike();
        }

        if (pin.isGutter()) {
            return StateFactory.gutter();
        }

        return StateFactory.number(pin);
    }
}
