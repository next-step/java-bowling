package bowling.domain;

import bowling.domain.state.State;

public interface Frame {
    State bowl(int countOfPins);
    Frame nextFrame();
}
