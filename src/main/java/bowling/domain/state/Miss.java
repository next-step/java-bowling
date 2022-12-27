package bowling.domain.state;

import bowling.domain.Pin;

public class Miss extends Finished {

    Pin firstPin;
    Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }
}
