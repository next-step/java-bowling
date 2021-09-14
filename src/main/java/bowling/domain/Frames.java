package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int FIRST_INDEX = 0;

    private List<Frame> frames = new ArrayList<>();

    public Frames() {
        frames.add(NormalFrame.of());
    }

    public int size() {
        return frames.size();
    }

    public Frame getFirstFrame() {
        return frames.get(FIRST_INDEX);
    }

    public Frame getFrame(int frameIndex) {
        return frames.get(frameIndex);
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }
}
