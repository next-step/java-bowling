package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Strike implements State {

    public static final String EXPRESSION = "X";

    @Override
    public State roll(final Pins pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        return  EXPRESSION;
    }
}
