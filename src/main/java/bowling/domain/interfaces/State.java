package bowling.domain.interfaces;

import bowling.domain.Pins;

public interface State {
    State bowl(Pins pins, int count);

    boolean isEnd();
}
