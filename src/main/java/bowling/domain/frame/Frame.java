package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.frame.state.State;

public interface Frame {

    int MAX_FRAME_NUMBER = 10;

    Frame bowl(final int pinCount);

    boolean isFinish();

    Frame createNext();

    Frame getNext();

    State getState();

    Frame findLast();

    Score getScore();
}
