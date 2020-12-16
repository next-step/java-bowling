package bowling.domain.frames;

public class AdjacentFrame {
    private final Frame previousFrame;
    private final Frame nextFrame;

    private AdjacentFrame(Frame previousFrame, Frame nextFrame) {
        this.previousFrame = previousFrame;
        this.nextFrame = nextFrame;
    }

    public static AdjacentFrame of(Frame previousFrame, Frame nextFrame) {
        return new AdjacentFrame(previousFrame, nextFrame);
    }

    public Frame getPreviousFrame() {
        return previousFrame;
    }

    public Frame getNextFrame() {
        return nextFrame;
    }
}
