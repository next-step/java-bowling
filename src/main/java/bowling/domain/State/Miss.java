package bowling.domain.State;

import static bowling.view.OutputView.COLUMN;

public class Miss extends State {
    private static final String SYMBOL = "-";

    private final State previous;

    public Miss(State previous) {
        this.previous = previous;
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String toString() {
        return previous + COLUMN + SYMBOL;
    }

    @Override
    public String toSimpleString() {
        return SYMBOL;
    }
}
