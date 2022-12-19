package bowling.model.state;

import bowling.model.Pin;

public class First implements State {

    private Pin pin;

    public First(Pin pin) {
        this.pin = pin;
    }
}
