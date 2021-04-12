package bowling.domain.State;

import bowling.domain.frame.PinCount;

public interface State {

    State newState(PinCount pinCount);

    boolean isClosed();

    String stateInString();

}
