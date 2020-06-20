package bowling.domain.frame;

import bowling.domain.state.FrameBowlStates;

public interface Frame {

    void play(int numberOfDownPin);

    boolean hasTurn();

    FrameBowlStates getBowlStates();
}
