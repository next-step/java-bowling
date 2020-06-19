package bowling.domain.frame;

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

    public Frame getNextFrame() {
        Frame currentFrame = getCurrentFrame();
        Frame nextFrame = currentFrame.next();
        if (currentFrame != nextFrame) {
            frames.add(nextFrame);
        }
        return nextFrame;
    }

    private Frame getCurrentFrame() {
        return frames.get(frames.size() - FRAMES_INDEX_CONST);
    }

    public boolean hasNextFrame() {
        return getCurrentFrame() != null;
    }

    public void bowl(int hitCounts) {
        getCurrentFrame().bowl(hitCounts);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
