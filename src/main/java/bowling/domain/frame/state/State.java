package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public interface State {
    String DELIMITER = "|";
    String FORMAT = "  %-3s ";

    State roll(Pins knockOverPins);

    boolean isTurnOver();

    String toResult();
}
