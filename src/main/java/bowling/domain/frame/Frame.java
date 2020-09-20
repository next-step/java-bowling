package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public abstract class Frame {

    protected static final int MIN_FRAME_INDEX = 1;
    protected static final int MAX_FRAME_INDEX = 10;

    protected int frameIndex;
    protected State state;
    protected Frame next;

    public Frame(int frameIndex) {
        validationFrameIndex(frameIndex);
        this.frameIndex = frameIndex;
        state = new Ready();
    }

    public int currentFrameIndex() {
        return frameIndex;
    }

    public Frame next() {
        int nextFrameIndex = frameIndex + 1;
        if (nextFrameIndex == MAX_FRAME_INDEX) {
            next = new FinalFrame(MAX_FRAME_INDEX);
            return next;
        }
        next = new NormalFrame(nextFrameIndex);
        return next;
    }

    protected abstract void validationFrameIndex(int frameIndex);

    public abstract boolean rollingEnd();

    public abstract boolean isEndAllFrame();

    public abstract String index();

    public abstract String currentFrameStatus();

    public abstract void bowl(Pin pin);

    protected abstract Score calculateAdditionalScore(Score score);

    public abstract Score score();

}
