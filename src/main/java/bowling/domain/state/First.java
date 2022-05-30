package bowling.domain.state;

import bowling.domain.score.Score;

public class First extends RunningState {
    public First(Pin pin) {
        super(pin);
    }

    @Override
    public State bowl(Pin pin) {
        Pin addedPin = this.pin.add(pin);

        if (pin.isZero()) {
            return new Miss(this);
        }

        if (addedPin.isTen()) {
            return new Spare(addedPin, this);
        }

        return new Second(pin, this);
    }

    @Override
    public Score score() {
        return Score.unScorable(pin);
    }

    @Override
    public String toString() {
        return pin.toString();
    }

    @Override
    public String toSimpleString() {
        return toString();
    }
}
