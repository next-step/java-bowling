package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrameSet extends FrameSet {

    private static final int FIRST_GAME_PLAY_COUNT = 1;
    private static final int END_NORMAL_FRAME_COUNT = 9;

    private NormalFrameSet(int playCount, State state) {
        super(playCount, state);
    }

    public static FrameSet createFirst() {
        return create(FIRST_GAME_PLAY_COUNT);
    }

    public static FrameSet create(int playCount) {
        return new NormalFrameSet(playCount, new Ready());
    }

    @Override
    public FrameSet readyNext() {
        if (playCount == END_NORMAL_FRAME_COUNT) {
            return LastFrameSet.create();
        }

        if (state.isEnd()) {
            return create(playCount + 1);
        }

        return this;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public FrameSet snapShot() {
        return new NormalFrameSet(playCount, state.snapShot());
    }
}
