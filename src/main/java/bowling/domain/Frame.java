package bowling.domain;

import bowling.domain.state.State;

import java.util.List;

public abstract class Frame {

    public static final int NO_SCORE = -1;

    protected int number;
    protected Frame nextFrame;

    protected Frame() {

    }

    public Frame getNextFrame() {
        return nextFrame;
    }

    public boolean isFinished() {
        return !canBowl();
    }

    public abstract boolean isLastFrame();

    public abstract boolean canBowl();

    public abstract void bowl(int number);

    public abstract Frame nextFrame();

    public abstract int getIntScore();

    public abstract Score calculateAdditionalScore(Score beforeScore);

    public abstract State getState();

    public abstract List<State> getStates();
}
