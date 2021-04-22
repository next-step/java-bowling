package bowling.domain.frame;

import bowling.domain.state.State;

public class NormalFrame implements Frame {
    private State state;
    private TryCount tryCount;

    private NormalFrame(TryCount tryCount) {
        this.tryCount = tryCount;
    }

    public static Frame of(int tryCount) {
        return new NormalFrame(TryCount.of(tryCount));
    }

    @Override
    public void bowl(int pinCount) {
    }

    @Override
    public Frame next() {
        return NormalFrame.of(0);
    }

    @Override
    public boolean isDone() {
        return tryCount.isMaxHit();
    }
}
