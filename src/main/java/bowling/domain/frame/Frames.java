package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int FRAMES_INDEX_CONST = 1;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initiate());
        return new Frames(frames);
    }

    public void moveToNextFrame() {
        Frame currentFrame = getCurrentFrame();
        Frame nextFrame = currentFrame.next();
        if (!currentFrame.equals(nextFrame)) {
            frames.add(nextFrame);
        }
    }

    public boolean hasNextFrame() {
        return getCurrentFrame() != null;
    }

    public void bowl(int hitCounts, BowlingPinsGroup bowlingPinsGroup) {
        getCurrentFrame().bowl(hitCounts, bowlingPinsGroup);
    }

    private Frame getCurrentFrame() {
        return frames.get(getCurrentFrameIndex() - FRAMES_INDEX_CONST);
    }

    public int getCurrentFrameIndex() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
