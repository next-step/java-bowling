package bowling.domain.state;

import bowling.domain.frame.Pin;

public interface State {
    boolean isFinished();

    State bowl(Pin pin);

    String viewString();
}
