package bowling.domain.frame;

import bowling.domain.state.PinsState;

public interface Pins {

    void down(int downPin);

    boolean hasTurn();

    PinsState getPinsState();
}
