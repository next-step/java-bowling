package bowling.domain.frame;

import java.util.Collections;
import java.util.List;

public class Frames {
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getFrameId(int index) {
        return frames.get(index).getFrameId();
    }

    public int getFrameScore(int frameId) {
        Frame frame = frames.get(frameId - 1);

        if (frame.isGutter() || frame.isMiss()) {
            return (frame.getFirstPoint() + frame.getSecondPoint());
        }

        if (frame.isSpare()) {
            int firstPointOfNextFrame = getNextFrameByCurrentId(frameId).getFirstPoint();
            return (frame.getFirstPoint() + frame.getSecondPoint() + firstPointOfNextFrame);
        }

        if (frame.isStrike()) {
            return findFrameScoreWhenStrike(frame);
        }

        return 0;
    }

    private Frame getNextFrameByCurrentId(int currentFrameId) {
        return frames.get(currentFrameId);
    }

    private int findFrameScoreWhenStrike(Frame currentFrame) {
        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());

        if (nextFrame.isStrike()) {
            int firstOfNext = nextFrame.getFirstPoint();
            int firstOfNextOfNext = getNextFrameByCurrentId(nextFrame.getFrameId()).getFirstPoint();
            return (currentFrame.getFirstPoint() + firstOfNext + firstOfNextOfNext);
        }

        int firstOfNext = nextFrame.getFirstPoint();
        int secondOfNext = nextFrame.getSecondPoint();
        return (currentFrame.getFirstPoint() + firstOfNext + secondOfNext);
    }
}