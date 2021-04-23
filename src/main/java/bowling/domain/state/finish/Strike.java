package bowling.domain.state.finish;

import bowling.domain.engine.State;
import bowling.domain.state.run.Hit;

import static bowling.domain.Constants.BOWLING_STATE_STRIKE;
import static bowling.domain.pin.Pin.BOWLING_PIN_MAX_SIZE;

public class Strike implements State {

    public Strike() {
    }

    @Override
    public State bowl(final int pin) {
        if (pin != BOWLING_PIN_MAX_SIZE) {
            return new Hit(pin);
        }
        return new Strike();
    }

    @Override
    public String printResult() {
        return BOWLING_STATE_STRIKE;
    }

    @Override
    public String printLastResult() {
        return BOWLING_STATE_STRIKE;
    }
}
