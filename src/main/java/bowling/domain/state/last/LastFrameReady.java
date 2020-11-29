package bowling.domain.state.last;

import bowling.domain.state.Gutter;
import bowling.domain.state.Ordinary;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

import static bowling.domain.pin.Pin.MAX_PINS;
import static bowling.domain.pin.Pin.MIN_PINS;

public class LastFrameReady implements State {
    private static final int LEFT_TRY = 2;

    @Override
    public State record(int pins) {
        if (pins == MAX_PINS) {
            return new Strike(LEFT_TRY);
        }
        if (pins == MIN_PINS) {
            return new Gutter(LEFT_TRY);
        }
        return new Ordinary(pins, LEFT_TRY);
    }
}
