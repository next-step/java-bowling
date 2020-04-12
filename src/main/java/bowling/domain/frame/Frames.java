package bowling.domain.frame;

import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int FINAL_FRAME_ID = 10;

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

        if (frame.isStrike()) {
            return findFrameScoreWhenStrike(frame);
        }

        if (frame.isSpare()) {
            int firstPointOfNextFrame = getNextFrameByCurrentId(frameId).getFirstPoint();
            return (frame.getFirstPoint() + frame.getSecondPoint() + firstPointOfNextFrame);
        }

        return (frame.getFirstPoint() + frame.getSecondPoint());
    }

    private Frame getNextFrameByCurrentId(int currentFrameId) {
        return frames.get(currentFrameId);
    }

    private int findFrameScoreWhenStrike(Frame currentFrame) {
        if (currentFrame.getFrameId() == FINAL_FRAME_ID) {
            return (currentFrame.getFirstPoint() + currentFrame.getThirdPoint() + currentFrame.getFourthPoint());
        }

        Frame nextFrame = getNextFrameByCurrentId(currentFrame.getFrameId());
        if (nextFrame.isStrike()) {
            int firstOfNext = nextFrame.getFirstPoint();
            int firstOfNextOfNext = getNextFrameByCurrentId(nextFrame.getFrameId()).getFirstPoint();
            return (currentFrame.getFirstPoint() + firstOfNext + firstOfNextOfNext);
        }

        return (currentFrame.getFirstPoint() + nextFrame.getFirstPoint() + nextFrame.getSecondPoint());
    }
}