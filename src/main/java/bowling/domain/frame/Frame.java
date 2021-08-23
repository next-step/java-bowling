package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.State;

public interface Frame {

    Frame bowl(Pins pins);

    boolean isFinish();

    State getState();
}
