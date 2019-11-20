package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class FrameSet {

    private final int playCount;
    private State state = new Ready();

    public FrameSet(int playCount) {
        this.playCount = playCount;
    }

    public FrameSet play(int hitCount) {
        state = state.play(hitCount);

        if (state.isEnd()) {
            return new FrameSet(playCount + 1);
        }
        return this;
    }

    public boolean isEnd() {
        return playCount > 10;
    }
}
