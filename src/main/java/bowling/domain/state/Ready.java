package bowling.domain.state;

import bowling.domain.Pins;

import static bowling.domain.Pin.BOWLING_PIN_MAX_SIZE;

public class Ready extends BaseState {

    @Override
    public FrameState bowl(int pin) {
        Pins first = Pins.first(pin);
        if (first.first() == BOWLING_PIN_MAX_SIZE) {
            return new Strike();
        }
        return new Hit(pin);
    }

    @Override
    public String printResult() {
        return "";
    }
}
