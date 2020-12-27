package step2.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int MAX_FRAME = 10;

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>(MAX_FRAME);
        return new Frames(frames);
    }

    public int getSize() {
        return frames.size();
    }

    public void bowl(Frame frame) {
        frames.add(frame);
    }

    public boolean isFinish() {
        return getSize() == MAX_FRAME;
    }

    public Frame nextFrame() {
        if (frames.size() > 9) {
            return null;
        }
//        return FinalFrame.init();
        return NormalFrame.init();
    }
}
