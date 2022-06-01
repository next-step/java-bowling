package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.view.OutputView.COLUMN;

public class Second extends SecondaryState {
    public Second(Pin pin, State previous) {
        super(pin, previous);
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

        return new Second(addedPin, this);
    }

    @Override
    public Score score() {
        return previous.score().bowl(pin);
    }

    @Override
    public String toString() {
        return previous + COLUMN + pin;
    }

    @Override
    public String toSimpleString() {
        return pin.toString();
    }
}
