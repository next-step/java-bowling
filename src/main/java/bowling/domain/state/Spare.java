package bowling.domain.state;

import bowling.domain.Pin;

public class Spare extends Finished {

    Pin firstPin;

    public Spare(Pin firstPin) {
        this.firstPin = firstPin;
    }
}
