package bowling.domain.state;

import bowling.domain.pin.Pins;

public interface State {
    boolean isFinish();

    State bowl(Pins pins);

    String print();
}
