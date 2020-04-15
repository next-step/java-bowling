package bowling.domain.frame;

import bowling.domain.turn.Turns;

public interface Frame {

    Frame bowl(final int pinCount);

    Turns getTurns();

    Frame createNext();
}
