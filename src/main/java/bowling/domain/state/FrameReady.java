package bowling.domain.state;

import bowling.domain.state.last.LastStrike;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class FrameReady implements State {
    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return new LastStrike();
        }
        if (pins == MIN_PINS) {
            return new Gutter();
        }
        return new Ordinary(pins);
    }
}
