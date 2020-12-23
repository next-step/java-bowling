package bowling.domain;

import bowling.domain.state.State;

public interface Frame {

    void playPitch(Pitch pitch);

    int getPitchSize();

    boolean isFinish();

    boolean isSpare();

    int getFrameScore();

    int getFirstScore();

    int getSecondScore();

    State bowl(Pitch pitch);

    State getState();
}
