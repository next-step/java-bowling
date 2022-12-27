package bowling.domain.state;

import bowling.domain.Pin;

public class FirstPin extends Running {

    private final Pin firstPin;

    public FirstPin(Pin firstPin) {
        this.firstPin = firstPin;
    }

    @Override
    public Status bowl(Pin secondPin) {
        if (firstPin.isClear(secondPin)) {
            return new Spare(firstPin);
        }
        return new Miss(firstPin, secondPin);
    }
}
