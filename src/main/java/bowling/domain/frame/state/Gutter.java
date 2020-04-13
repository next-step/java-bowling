package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Gutter implements State {
    private static final String EXPRESSION = "-";

    @Override
    public State roll(final Pins knockOverPins) {
        return null;
    }

    @Override
    public String toString() {
        return EXPRESSION;
    }
}
