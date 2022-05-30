package bowling.domain.frame;

import bowling.domain.state.State;

public interface Frame {

    Frame bowling(int pins);

    Frame next(int pins);

    int round();

    boolean isFinalFrame();

    boolean isFinishBowling();

    String currentResult();

    State state();
}
