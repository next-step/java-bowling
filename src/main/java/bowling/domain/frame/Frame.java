package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;

import java.util.List;
import java.util.Optional;

public interface Frame {
    Frame bowl(Pins pins);

    boolean isEnd();

    Optional<Frame> getNext();

    FrameNumber getFrameNumber();

    String getState();

    States getStates();
}
