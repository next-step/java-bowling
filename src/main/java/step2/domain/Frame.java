package step2.domain;

import step2.domain.state.State;

public interface Frame {

    void bowl(Pitch pitch);

    boolean isFinish();

    int getSize();

    void validateScore(Pitch pitch);

    State getState();

}
