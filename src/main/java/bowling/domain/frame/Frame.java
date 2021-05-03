package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.frame.state.State;
import bowling.domain.frame.state.StateHistory;

public abstract class Frame {
    protected StateHistory stateHistory;

    protected Frame() {
        stateHistory = new StateHistory();
    }

    public boolean isAvailable() {
        return stateHistory.getLatestState().hasNext();
    }

    public abstract void bowl(int inputScore);

    public abstract int getScore();

    protected abstract int additionalScore(Score score);

    public abstract Frame createFrame(boolean isFinalFrame);

    public State getLatestState() {
        return stateHistory.getLatestState();
    }
}
