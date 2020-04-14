package bowling.domain.frame;

import bowling.domain.state.State;

public interface Frame {

    void play(int felledPin);

    State getStatus();

    Frame getNext();

    int getFrameNumber();

}
