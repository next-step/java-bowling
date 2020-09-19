package bowling.domain.state;

import bowling.domain.pin.Pin;

public class Miss extends FinishedState {

    private final Pin first;
    private final Pin second;

    private Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public static State of(Pin firstPin, Pin felledPin) {
        return new Miss(firstPin, felledPin);
    }
}
