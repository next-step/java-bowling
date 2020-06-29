package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int BEFORE_LAST_INDEX = 9;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.init());
        return new Frames(frames);
    }

    public void bowl(int downPin) {
        if (currentFrame().isLastTryAtFrame()) {
            frames.add(nextFrame());
        }
        currentFrame().bowl(downPin);
    }

    public boolean isLastTryAtFrame() {
        return currentFrame().isLastTryAtFrame();
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    private Frame nextFrame() {
        if (frames.size() == BEFORE_LAST_INDEX)
            return FinalFrame.init();
        return currentFrame().next();
    }

    public Frame getFrame(int index) {
        return frames.get(index);
    }
}
