package bowling.frame;

import bowling.dto.StateDto;
import bowling.pin.Pin;

public interface Frame {
    boolean hasTurn();
    void play(final Pin pin);
    StateDto currentState();
}
