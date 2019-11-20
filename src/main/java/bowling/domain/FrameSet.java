package bowling.domain;

import bowling.domain.state.State;

public abstract class FrameSet {

    protected final int playCount;
    protected State state;

    protected FrameSet(int playCount, State state) {
        this.playCount = playCount;
        this.state = state;
    }

    public State play(int hitCount) {
        state = state.play(hitCount);
        return state;
    }

    public abstract FrameSet readyNext();

    public abstract boolean isEnd();

    public abstract FrameSet snapShot();

    public State getState() {
        return state.snapShot();
    }

    public boolean isEndedState() {
        return state.isEnd();
    }

    public int getPlayCount() {
        return playCount;
    }
}
