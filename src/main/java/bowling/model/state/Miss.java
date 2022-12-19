package bowling.model.state;

import bowling.model.Pin;

public class Miss implements State {

    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }
}
