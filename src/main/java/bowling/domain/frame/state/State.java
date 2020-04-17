package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public interface State {
    State roll(Pins knockOverPins);

    boolean isTurnOver();

    String toResult();

    int getKnockOverCount();
}
