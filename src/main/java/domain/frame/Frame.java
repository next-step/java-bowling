package domain.frame;

import domain.Pins;
import domain.state.State;

public interface Frame {

    State setKnockedDownPins(Pins knockedDown);

    State getState();
}
