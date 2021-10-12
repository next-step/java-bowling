package bowling.domain.frame;

public abstract class AbstractFrame implements Frame {

    private final FrameNumber frameNumber;

    protected AbstractFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }
}
