package domain.state;

import domain.Pins;
import domain.state.State;

public class Strike implements State {
    @Override
    public State update(Pins fallenPins) {
        return null;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
