package step2.domain.frame;

import step2.domain.Pitch;
import step2.domain.state.State;

public interface Frame {

    int MAX_SIZE = 3;
    int NORMAL_FRAME_SIZE = 2;
    int MIN_SIZE = 1;

    void bowl(Pitch pitch);

    boolean isFinish();

    int getSize();

    void validateScore(Pitch pitch);

    State getState();

}
