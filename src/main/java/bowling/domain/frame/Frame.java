package bowling.domain.frame;

import bowling.domain.score.Pins;
import bowling.domain.state.State;

public abstract class Frame {
    public static final int FRAME_INIT = 0;

    public static Frame init() {
        return new NormalFrame(FRAME_INIT);
    }

    public abstract Frame next();

    public abstract void bowl(Pins pins);

    public abstract void secondBowl(int userIndex, State state, Pins pins);

    public abstract void thirdBowl(int userIndex, State state, Pins thirdPins);

    public abstract State getState(int userIndex);

    public abstract String getSymbol(int userIndex);

    public abstract int getFrameScore(int userIndex);

    public abstract int getFirstScore(int userIndex);

    public abstract int getSecondScore(int userIndex);

    public abstract State getLastState();

    public abstract int getLastStateFrameScore();

    public abstract int getLastStateFirstScore();

    public abstract int getLastStateSecondScore();

    public abstract String getLastStateSymbol();
}
