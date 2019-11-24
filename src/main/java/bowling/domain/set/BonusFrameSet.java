package bowling.domain.set;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class BonusFrameSet implements FrameSet {

    public static final int PLAY_COUNT = 11;

    private final FrameSet frameSet;

    private BonusFrameSet(State state) {
        this.frameSet = BaseFrameSet.create(BonusFrameSet.PLAY_COUNT, state);
    }

    public static BonusFrameSet create() {
        return new BonusFrameSet(new Ready());
    }

    @Override
    public void play(int hitCount) {
        frameSet.play(hitCount);
    }

    @Override
    public FrameSet next() {
        return this;
    }

    @Override
    public boolean isEnd() {
        return !(getState() instanceof Ready);
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
        return BonusFrameSet.PLAY_COUNT;
    }
}
