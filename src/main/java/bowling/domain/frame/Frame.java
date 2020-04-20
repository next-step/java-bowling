package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.score.Score;

public interface Frame {

    int MAX_FRAME_NUMBER = 10;

    Frame bowl(final int pinCount);

    boolean isFinish();

    boolean isEnd();

    Frame createNext();

    Frame getNext();

    State getState();

    Frame findLast();

    Score getCurrentScore();

    Score getCalculateScore(Score before);
}
