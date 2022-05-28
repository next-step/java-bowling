package bowling.domain.State;

public class First extends State {
    private final Pin pin;

    public First(Pin pin) {
        this.pin = pin;
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isZero()) {
            return new Miss(this);
        }

        Pin addedPin = this.pin.add(pin);

        if (addedPin.isTen()) {
            return new Spare(addedPin, this);
        }

        return new Second(addedPin, this);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String toString() {
        return pin.toString();
    }
}
