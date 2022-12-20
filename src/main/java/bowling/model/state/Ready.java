package bowling.model.state;

import bowling.model.Pin;

public class Ready extends Running {

    @Override
    public State bowl(Pin pin) {
        if (pin.isClearAll()) {
            return new Strike();
        }
        return new First(pin);
    }

    @Override
    public String toString() {
        return String.format("%-4s", " ");
    }
}
