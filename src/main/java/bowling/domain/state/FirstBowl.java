package bowling.domain.state;

import bowling.domain.score.Pin;

public class FirstBowl extends Running {

    public FirstBowl(int pin) {
        firstPin = new Pin(pin);
    }

    @Override
    public State bowl(int secondCount) {
        if (firstPin.count() + secondCount == MAX_PIN_NO) {
            return new Spare(firstPin.count(), secondCount);
        }
        return new Miss(firstPin.count(), secondCount);
    }
}
