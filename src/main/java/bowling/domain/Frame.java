package bowling.domain;

import bowling.domain.turn.Turns;

public interface Frame {

    Frame bowl(final int pinCount);

    Turns getTurns();
}
