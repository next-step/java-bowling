package bowling.domain.frame;

import bowling.domain.state.State;
import bowling.domain.state.StateHistory;

import java.util.List;

public interface Frame {

    void play(int felledPin);

    State getState();

    Frame getNext();

    int getFrameNumber();

    boolean isEndedFrame();

    boolean isLastFrame();

    StateHistory getStateHistory();
}
