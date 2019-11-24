package bowling.domain.set;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class LastFrameSet implements FrameSet {

    public static final int PLAY_COUNT = 10;

    private final FrameSet frameSet;

    private LastFrameSet(State state) {
        this.frameSet = BaseFrameSet.create(LastFrameSet.PLAY_COUNT, state);
    }

    public static FrameSet create() {
        return new LastFrameSet(new Ready());
    }

    @Override
    public void play(int hitCount) {
        frameSet.play(hitCount);
    }

    @Override
    public FrameSet readyNext() {
        if (getState().isBonusPlayableState()) {
            return BonusFrameSet.create();
        }

        return this;
    }

    @Override
    public boolean isEnd() {
        return getState().isEnd();
    }

    @Override
    public FrameSet snapShot() {
        return frameSet.snapShot();
    }

    @Override
    public State getState() {
        return frameSet.getState();
    }

    @Override
    public int getPlayCount() {
        return LastFrameSet.PLAY_COUNT;
    }
}
