package bowling.domain.state;

import bowling.domain.Pin;

public interface State {

    State bowl(Pin pin);
    boolean isFinished();
}
