package bowling.domain;

import bowling.domain.state.State;

public interface Frame {
    Frame bowl(int countOfPins);
    State getState();
    String expression();
}
