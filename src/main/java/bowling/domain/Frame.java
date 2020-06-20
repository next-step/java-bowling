package bowling.domain;

import java.util.List;

public interface Frame {

    void play(int numberOfDownPin);

    boolean hasTurn();

    List<FrameBowlState> getBowlStates();
}
