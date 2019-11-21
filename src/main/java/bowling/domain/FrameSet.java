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
        assertHitCount(hitCount);

        state = state.play(hitCount);
        return state;
    }

    private void assertHitCount(int hitCount) {
        if (hitCount > State.INIT_PIT_COUNT || hitCount < 0) {
            throw new IllegalArgumentException("넘어트린 핀의 개수가 올바르지 않습니다.");
        }
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
