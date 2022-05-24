package bowling.domain;

import bowling.domain.state.State;

public interface Frame {
    Frame bowl(int countOfPins);
    Frame nextFrame();
    State getState();
    String expression();
}
