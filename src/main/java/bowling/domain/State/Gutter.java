package bowling.domain.State;

public class Gutter extends State {
    private static final String SYMBOL = "-";

    @Override
    public State bowl(Pin pin) {
        if (pin.isTen()) {
            return new Spare(pin, this);
        }

        if (pin.isZero()) {
            return new Miss(this);
        }

        return new Second(pin, this);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String toString() {
        return SYMBOL;
    }
}
