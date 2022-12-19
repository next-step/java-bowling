package bowling.model.state;

import bowling.model.Pin;

public class Ready implements State {

    public State bowl(Pin pin) {
        if (pin.isClearAll()) {
            return new Strike();
        }
        return new First(pin);
    }
}
