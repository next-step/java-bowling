package domain.frame;

import domain.Pins;
import domain.state.State;

public interface Frame {

    Frame setKnockedDownPins(Pins knockedDown);

    State getState();

    boolean isClosed();
}
