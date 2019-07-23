package domain.state.closed;

import domain.Pins;
import domain.state.State;

abstract class Closed implements State {

    @Override
    public State update(Pins fallenPins) {
        throw new ClosedFrameException();
    }

    @Override
    public boolean isClosed() {
        return Boolean.TRUE;
    }
}
