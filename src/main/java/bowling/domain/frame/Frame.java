package bowling.domain.frame;

import bowling.domain.frame.state.State;
import bowling.domain.frame.state.States;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.Optional;

public interface Frame {
    Frame bowl(Pins pins);

    boolean isEnd();

    FrameNumber getFrameNumber();

    Optional<Frame> getNext();

    States getStates();

    State getCurrentState();

    Score getScore();

    Score calculateAdditionalScore(Score score);
}
