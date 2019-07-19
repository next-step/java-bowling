package domain.state;

import domain.Pins;

public class Strike implements State {
    private static final String STRIKE_SYMBOL = "X";

    @Override
    public State update(Pins fallenPins) {
        throw new ClosedFrameException();
    }

    @Override
    public boolean isClosed() {
        return Boolean.TRUE;
    }

    @Override
    public String printState() {
        return STRIKE_SYMBOL;
    }
}
