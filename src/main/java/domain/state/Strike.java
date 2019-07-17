package domain.state;

import domain.Pins;

public class Strike implements State {

    public State updateOnFinalFrame(Pins fallenPins) {
        if (fallenPins.isStrike()) {
            return new Strike();
        }
        return new Hit(fallenPins);
    }

    @Override
    public State update(Pins fallenPins) {
        throw new ClosedFrameException();
    }

    @Override
    public boolean isClosed() {
        return Boolean.TRUE;
    }
}
