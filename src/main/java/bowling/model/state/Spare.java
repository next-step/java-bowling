package bowling.model.state;

import bowling.model.Pin;

public class Spare implements State {

    private final Pin firstPin;
    private final Pin secondPin;

    public Spare(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }
}
