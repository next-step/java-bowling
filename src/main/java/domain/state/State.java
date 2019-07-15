package domain.state;

import domain.Pins;

public interface State {
    State bowl(Pins downPins);

    Boolean isClosed();
}
