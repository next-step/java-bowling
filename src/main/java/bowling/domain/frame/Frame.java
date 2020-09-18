package bowling.domain.frame;

import bowling.domain.Pin;

public abstract class Frame {

    protected static final int MIN_FRAME_INDEX = 1;
    protected static final int MAX_FRAME_INDEX = 10;

    protected int frameIndex;

    public Frame(int frameIndex) {
        this.frameIndex = frameIndex;

        validationFrameIndex(frameIndex);
    }

    public int currentFrameIndex() {
        return frameIndex;
    }

    public Frame next() {
        int nextFrameIndex = frameIndex + 1;
        if (nextFrameIndex == MAX_FRAME_INDEX) {
            return new FinalFrame(MAX_FRAME_INDEX);
        }

        return new NormalFrame(nextFrameIndex);
    }

    abstract protected void validationFrameIndex(int frameIndex);

    abstract public boolean rollingEnd();

    abstract public boolean isEndAllFrame();

    abstract public String index();

    abstract public String currentFrameStatus();

    abstract void bowl(Pin pin);

}
