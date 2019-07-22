package domain.bowling;

import domain.Pins;
import domain.state.State;

public interface Bowling {

    Bowling bowl(Pins downPins);
    State getFrameState();
}
