package bowling.domain.frame;

import bowling.domain.state.PitchState;

public abstract class Round {
    public static final int FINAL_MAX_COUNT = 3;
    public static final int MIN_FRAME = 1;
    public static final int MAX_FRAME = 10;

    public abstract boolean isMaxRound();
    public abstract Frame nextFrame(Frame frame);
    public abstract Round next(int point);
    public abstract PitchState getPitchState();
    public abstract int getRound();
    public abstract boolean isEnd();
}
