package bowling.domain;

public abstract class Frame {

    protected static final int MIN_FRAME_INDEX = 1;
    protected static final int MAX_FRAME_INDEX = 10;

    protected int frameIndex;

    public Frame(int frameIndex) {
        this.frameIndex = frameIndex;

        validationFrameIndex(frameIndex);
    }

    abstract protected void validationFrameIndex(int frameIndex);

    abstract public boolean rollingEnd();

    abstract public boolean allFrameEnd();

    abstract public String index();

    abstract public String currentFrameStatus();

    abstract void bowl(Pin pin);

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
}
