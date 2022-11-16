package bowling.domain.frame;

import java.util.List;

import bowling.domain.frame.state.State;

public abstract class Frame {
    public static final int START_FRAME = 1;
    public static final int LAST_FRAME = 10;

    public abstract void bowl(int falledPins);

    public abstract Frame createNextFrame();

    public abstract Score getScore();

    public abstract Score calculateBonusScore(Score previousFrameScore);

    public abstract int getFrameNumber();

    public abstract boolean isFrameFinish();

    public abstract List<State> getStates();
}
