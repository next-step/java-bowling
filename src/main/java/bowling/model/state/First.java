package bowling.model.state;

import bowling.model.Pin;

public class First implements State {

    private final Pin pin;

    public First(Pin pin) {
        this.pin = pin;
    }

    public State bowl(Pin pin) {
        if (this.pin.isClearAll(pin)) {
            return new Spare(this.pin, pin);
        }
        return new Miss(this.pin, pin);
    }
}
