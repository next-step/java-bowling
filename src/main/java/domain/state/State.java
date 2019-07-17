package domain.state;

import domain.Pins;

public interface State {

    State update(Pins fallenPins);

    boolean isClosed();
}
