package bowling.domain.State;

import static bowling.view.OutputView.COLUMN;

public class Spare extends EndedState {
    private static final String SYMBOL = "/";

    private final Pin pin;
    private final State previous;

    public Spare(Pin pin, State previous) {
        this.pin = pin;
        this.previous = previous;
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
        return previous + COLUMN + SYMBOL;
    }

    public String toSimpleString() {
        return SYMBOL;
    }
}
