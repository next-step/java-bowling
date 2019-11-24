package bowling.domain.set;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class LastFrameSet implements FrameSet {

    private static final int PLAY_COUNT = 10;
    private State state;

    private LastFrameSet(State state) {
        this.state = state;
    }

    public static FrameSet create() {
        return new LastFrameSet(new Ready());
    }

    @Override
    public void play(int hitCount) {
        state = state.play(hitCount);
    }

    @Override
    public FrameSet readyNext() {
        if (state.isBonusPlayableState()) {
            return BonusFrameSet.create();
        }

        return this;
    }

    @Override
    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public FrameSet snapShot() {
        return new LastFrameSet(state.snapShot());
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getPlayCount() {
        return LastFrameSet.PLAY_COUNT;
    }
}
