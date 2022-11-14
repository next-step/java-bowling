package bowling.domain;

import bowling.domain.state.State;

import java.util.List;

public interface Frame {

    int getFrameNumber();

    boolean isLastFrame();

    boolean canBowl();

    void bowl(final int number);

    Frame nextFrame();

    int getIntScore();

    Score calculateAdditionalScore(final Score beforeScore);

    State getState();

    List<State> getStates();

    boolean isFinished();

    Frame getNextFrame();
}
