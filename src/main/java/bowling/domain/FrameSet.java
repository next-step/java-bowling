package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FrameSet {

    private final int playCount;
    private State state = new Ready();

    public static FrameSet createFirst() {
        return new FrameSet(1);
    }

    private FrameSet(int playCount) {
        this.playCount = playCount;
    }

    public State play(int hitCount) {
        state = state.play(hitCount);
        return state;
    }

    public FrameSet readyNextSet() {
        if (state.isEnd()) {
            return new FrameSet(playCount + 1);
        }

        return this;
    }

    public State getState() {
        return state;
    }

    public int getPlayCount() {
        return playCount;
    }

    public boolean isEnd() {
        return playCount > 10;
    }
}
