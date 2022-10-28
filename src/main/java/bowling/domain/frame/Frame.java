package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.state.State;

import java.util.List;

public interface Frame {
    Frame bowl(FallenPin fallenPin);

    boolean isFinished();

    List<State> getStates();
}
