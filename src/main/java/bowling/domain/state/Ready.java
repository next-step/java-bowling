package bowling.domain.state;

import bowling.domain.Pin;

public class Ready extends Running {
    @Override
    public Status bowl(Pin pin) {
        if (pin.isMax()) {
            return new Strike();
        }
        return new FirstPin(pin);
    }
}
