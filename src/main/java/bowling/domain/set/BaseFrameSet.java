package bowling.domain.set;

import bowling.domain.state.State;

public class BaseFrameSet implements FrameSet {

    private static final int MIN_PLAY_COUNT = 1;
    private static final int MIN_HIT_COUNT = 0;
    private static final int MAX_HIT_COUNT = 10;

    private final int playCount;
    private State state;

    private BaseFrameSet(int playCount, State state) {
        assertPlayCount(playCount);

        this.playCount = playCount;
        this.state = state;
    }

    @Override
    public void play(int hitCount) {
        assertHitCount(hitCount);
        state = state.play(hitCount);
    }

    @Override
    public FrameSet readyNext() {
        return this;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public FrameSet snapShot() {
        return new BaseFrameSet(playCount, state);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getPlayCount() {
        return playCount;
    }

    public static BaseFrameSet create(int playCount, State state) {
        return new BaseFrameSet(playCount, state);
    }

    private void assertPlayCount(int playCount) {
        if (playCount < MIN_PLAY_COUNT || playCount > BonusFrameSet.PLAY_COUNT) {
            throw new IllegalArgumentException("게임 세트 횟수가 올바르지 않습니다.");
        }
    }

    private void assertHitCount(int hitCount) {
        if (hitCount < MIN_HIT_COUNT || hitCount > MAX_HIT_COUNT) {
            throw new IllegalArgumentException("넘어트린 핀 갯수가 올바르지 않습니다.");
        }
    }
}
