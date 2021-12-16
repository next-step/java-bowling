package bowling.domain;

import bowling.domain.state.State;
import bowling.strategy.PitchNumberStrategy;

public interface Frame {
    void run(PitchNumberStrategy numberStrategy);

    Frame next();

    FrameInfo info();

    void changeState(State state);

    State state();
}
