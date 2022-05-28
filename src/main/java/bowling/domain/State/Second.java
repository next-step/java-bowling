package bowling.domain.State;

import static bowling.view.OutputView.COLUMN;

public class Second extends State {
    private final Pin pin;
    private final State previous;

    public Second(Pin pin, State previous) {
        this.pin = pin;
        this.previous = previous;
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
    public boolean isDone() {
        return true;
    }

    public boolean isSecond() {
        return true;
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
