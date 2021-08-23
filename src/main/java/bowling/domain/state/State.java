package bowling.domain.state;

import bowling.domain.Pins;

public interface State {
    State nextPitch(Pins pins);
    String display();
}
