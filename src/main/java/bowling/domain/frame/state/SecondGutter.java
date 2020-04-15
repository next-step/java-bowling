package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class SecondGutter implements State {
    private static final String expression = "-";

    @Override
    public State roll(final Pins second) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        return expression;
    }
}
