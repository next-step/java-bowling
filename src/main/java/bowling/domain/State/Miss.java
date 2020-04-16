package bowling.domain.State;

import bowling.domain.Pin;

public class Miss extends Finished {
    private final Pin firstPin;
    private final Pin secondPin;

    public Miss(Pin firstPin, Pin secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    @Override
    public String getDisplayText() {
        return firstPin.getDisplayText(secondPin);
    }
}
