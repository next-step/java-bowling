package bowling.domain.State;

public class First extends State {
    private final Pin pin;

public class First extends RunningState {
    public First(Pin pin) {
        this.pin = pin;
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
    public boolean isDone() {
        return false;
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
