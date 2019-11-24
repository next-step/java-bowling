package bowling.domain.set;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class BonusFrameSet implements FrameSet {

    private static final int PLAY_COUNT = 11;

    private State state;

    private BonusFrameSet(State state) {
        this.state = state;
    }

    public static BonusFrameSet create() {
        return new BonusFrameSet(new Ready());
    }

    @Override
    public void play(int hitCount) {
        state = state.play(hitCount);
    }

    @Override
    public FrameSet readyNext() {
        return this;
    }

    @Override
    public boolean isEnd() {
        return !(state instanceof Ready);
    }

    @Override
    public FrameSet snapShot() {
        return new BonusFrameSet(state);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getPlayCount() {
        return BonusFrameSet.PLAY_COUNT;
    }
}
