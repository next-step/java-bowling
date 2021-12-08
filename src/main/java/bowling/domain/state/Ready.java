package bowling.domain.state;

import bowling.domain.Pin;

public class Ready extends Running {
    @Override
    public State bowl(Pin pin) {
        if (pin.isMaxCount()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }
}
