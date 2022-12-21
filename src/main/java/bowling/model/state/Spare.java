package bowling.model.state;

import bowling.model.Pin;

public class Spare extends Finished {

    private final Pin firstPin;
    private final Pin secondPin;

    public Spare(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String toString() {
        return firstPin.getValue() + "|/";
    }
}
