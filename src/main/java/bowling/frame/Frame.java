package bowling.frame;

import bowling.dto.StateDtos;
import bowling.pin.Pin;

public interface Frame {
    boolean hasTurn();
    void play(final Pin pin);
    StateDtos convert();
}
