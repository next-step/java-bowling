package bowling.domain.state;

import bowling.domain.pins.Pins;

public interface State {

    State bowl(Pins pins);

    boolean isFinish();
}
