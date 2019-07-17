package domain.state;

import domain.Pins;

public class Strike implements State {
    @Override
    public State update(Pins fallenPins) {
        throw new ClosedFrameException();
    }

    @Override
    public boolean isClosed() {
        return Boolean.TRUE;
    }
}
