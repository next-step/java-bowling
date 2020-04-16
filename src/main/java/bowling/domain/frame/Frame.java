package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;

import java.util.Optional;

public interface Frame {
    Frame bowl(Pins pins);

    boolean isEnd();

    Optional<Frame> getNext();

    FrameNumber getFrameNumber();

    States getStates();

    State getCurrentState();
}
