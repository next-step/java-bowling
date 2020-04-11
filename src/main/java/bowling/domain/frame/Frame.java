package bowling.domain.frame;

import bowling.domain.state.Pin;
import bowling.domain.state.State;

public interface Frame {

    Frame bowl(Pin fallenPins);

    Score calculateByBeforeScore(Score beforeScore);

    State getState();

    int getFrameNumber();

    void updateScore(Score score);

    Result getFrameResult();

    Score getScore();
}
