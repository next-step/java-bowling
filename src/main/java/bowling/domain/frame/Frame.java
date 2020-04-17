package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public interface Frame {
    Frame bowl(Pins pins);

    boolean isEnd();

    FrameNumber getFrameNumber();

    States getStates();

    State getCurrentState();

    Score getScore();

    Score calculateAdditionalScore(Score score);
}
