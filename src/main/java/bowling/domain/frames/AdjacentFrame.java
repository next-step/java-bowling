package bowling.domain.frames;

public class AdjacentFrame {
    private final FrameImpl previousFrame;
    private final FrameImpl nextFrame;

    private AdjacentFrame(FrameImpl previousFrame, FrameImpl nextFrame) {
        this.previousFrame = previousFrame;
        this.nextFrame = nextFrame;
    }

    public static AdjacentFrame of(FrameImpl previousFrame, FrameImpl nextFrame) {
        return new AdjacentFrame(previousFrame, nextFrame);
    }

    public FrameImpl getPreviousFrame() {
        return previousFrame;
    }

    public FrameImpl getNextFrame() {
        return nextFrame;
    }
}
