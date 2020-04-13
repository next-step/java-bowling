package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public interface State {
    String DELIMITER = "|";

    State roll(Pins knockOverPins);

    boolean isTurnOver();
}
