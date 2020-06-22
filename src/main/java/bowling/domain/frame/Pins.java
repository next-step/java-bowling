package bowling.domain.frame;

import bowling.domain.state.FrameBowlStates;

public interface Pins {

    void down(int downPin);

    boolean hasTurn();

    FrameBowlStates getBowlStates();
}
