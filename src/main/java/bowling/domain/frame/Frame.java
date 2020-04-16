package bowling.domain.frame;

import bowling.domain.frame.state.State;

public interface Frame {

    int MAX_FRAME_NUMBER = 10;

    Frame bowl(final int pinCount);

    Frame createNext();

    Frame getNext();

    boolean isFinish();

    State getState();
}
