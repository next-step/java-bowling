package bowling.domain.state;

import bowling.domain.Pin;

public interface State {
    boolean isFinished();

    State bowl(Pin pin);
}
