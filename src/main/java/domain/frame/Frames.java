package domain.frame;

import domain.Pins;

import java.util.List;

public class Frames {

    private List<Frame> frames;
    private FrameIndex currentFrameIndex;

    private Frames(List<Frame> frames) {
        this.frames = frames;
        this.currentFrameIndex = FrameIndex.initiate();
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    private Frame getCurrentFrame() {
        return frames.get(currentFrameIndex.value());
    }

    public Frames bowl(Pins downPins) {
        Frame currentFrame = getCurrentFrame();
        Frame frame = currentFrame.setKnockedDownPins(downPins);
        if (frame.isClosed()) {
            currentFrameIndex = currentFrameIndex.next();
        }
        return this;
    }

    public boolean isFinished() {
        return currentFrameIndex.isStop();
    }

    public FrameIndex getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
