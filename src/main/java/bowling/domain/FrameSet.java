package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FrameSet {

    private final int playCount;
    private State state;

    public static FrameSet create(int playCount) {
        return new FrameSet(playCount, new Ready());
    }

    private FrameSet(int playCount, State state) {
        this.playCount = playCount;
        this.state = state;
    }

    public State play(int hitCount) {
        state = state.play(hitCount);
        return state;
    }

    public FrameSet readyNext() {
        if (state.isEnd()) {
            return FrameSet.create(playCount + 1);
        }

        return this;
    }

    public State getState() {
        return state.snapShot();
    }

    public boolean isEndedState() {
        return state.isEnd();
    }

    public int getPlayCount() {
        return playCount;
    }

    public boolean isEnd() {
        return playCount > 10;
    }

    public FrameSet snapShot() {
        return new FrameSet(playCount, state.snapShot());
    }
}
