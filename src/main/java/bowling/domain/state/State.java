package bowling.domain.state;

import bowling.domain.pin.Pin;

public interface State {

    State bowl(Pin felledPin);

    boolean isEnd();

    String view();
}
