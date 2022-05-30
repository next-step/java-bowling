package bowling.domain.state;

import bowling.domain.score.Score;

import static bowling.view.OutputView.COLUMN;

public class Miss extends SecondaryState {
    private static final String SYMBOL = "-";

    public Miss(State previous) {
        this(Pin.zero(), previous);
    }

    public Miss(Pin pin, State previous) {
        super(pin, previous);
    }

    @Override
    public State bowl(Pin pin) {
        return null;
    }

    @Override
    public Score score() {
        return previous.score().bowl(pin);
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
