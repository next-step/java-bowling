package bowling.domain.State;

public class Strike extends State {
    private static final String SYMBOL = "X";

    private final Pin pin;

    public Strike(Pin pin) {
        this.pin = pin;
    }

    @Override
    public State bowl(Pin pin) {
        if (pin.isTen()) {
            return new Strike(pin);
        }

        if (pin.isZero()) {
            return new Gutter();
        }

        return new First(pin);
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String toString() {
        return SYMBOL;
    }

    @Override
    public String toSimpleString() {
        return toString();
    }
}
