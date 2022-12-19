package bowling.model.state;

import bowling.model.Pin;

public interface State {

    State bowl(Pin pin);
}
