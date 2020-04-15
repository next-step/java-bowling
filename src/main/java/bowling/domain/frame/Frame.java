package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;

import java.util.Optional;

public interface Frame {
    String SEPARATOR = ",";

    Frame bowl(Pins pins);

    boolean isEnd();

    Optional<Frame> getNext();

    FrameNumber getFrameNumber();

    String getStates();

    String getState();
}
