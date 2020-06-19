package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FramesGroup {

    private final List<Frame> frames;

    private FramesGroup(List<Frame> frames) {
        this.frames = frames;
    }

    public static FramesGroup initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initiate());
        return new FramesGroup(frames);
    }

    public void bowl(int hitCounts) {
        frames.get(frames.size() - 1).bowl(hitCounts);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public Frame getCurrentFrame() {
        NormalFrame frame = (NormalFrame) frames.get(frames.size() - 1);
        NormalFrame nextFrame = (NormalFrame) frame.next();
        if (frame != nextFrame) {
            frames.add(nextFrame);
        }
        return nextFrame;
    }
}
