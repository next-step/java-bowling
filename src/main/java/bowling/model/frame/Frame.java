package bowling.model.frame;

import bowling.model.Pin;
import bowling.model.state.State;

public interface Frame {

    void bowl(Pin pin);
    boolean isFinished();

    Frame nextFrame();

    boolean isFinalFrame();

    int getNumber();

    State getState();
}
