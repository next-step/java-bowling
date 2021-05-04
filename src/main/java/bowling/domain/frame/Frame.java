package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.frame.state.State;
import bowling.domain.frame.state.StateHistory;

public abstract class Frame {
    protected StateHistory stateHistory;

    protected Frame() {
        stateHistory = new StateHistory();
    }

    public abstract boolean isAvailable();

    public abstract State bowl(int inputScore);

    public abstract Score getScore();

    protected abstract Score additionalScore(Score score);

    public abstract Frame createFrame(boolean isFinalFrame);

    public State getLatestState() {
        return stateHistory.getLatestState();
    }
}
