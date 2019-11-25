package bowling.domain.set;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrameSet implements FrameSet {

    public static final int START_SET_PLAY_COUNT = 1;
    public static final int END_SET_PLAY_COUNT = 9;

    private final FrameSet frameSet;

    private NormalFrameSet(int playCount, State state) {
        assertPlayCount(playCount);
        this.frameSet = BaseFrameSet.create(playCount, state);
    }

    public static FrameSet createFirst() {
        return create(START_SET_PLAY_COUNT);
    }

    public static FrameSet create(int playCount) {
        return new NormalFrameSet(playCount, new Ready());
    }

    @Override
    public void play(int hitCount) {
        frameSet.play(hitCount);
    }

    @Override
    public FrameSet next() {
        if (getPlayCount() == NormalFrameSet.END_SET_PLAY_COUNT) {
            return LastFrameSet.create();
        }

        if (getState().isEnd()) {
            return create(getPlayCount() + 1);
        }

        return this;
    }

    @Override
    public boolean isEnd() {
        return false;
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
        return frameSet.getPlayCount();
    }

    private void assertPlayCount(int playCount) {
        if (playCount > END_SET_PLAY_COUNT) {
            throw new IllegalArgumentException("일반 세트는 더이상 진행할 수 없습니다.");
        }
    }
}
