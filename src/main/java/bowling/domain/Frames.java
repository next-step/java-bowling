package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    public static final int NORMALFRAME_NUMBER = 9;

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>(NORMALFRAME_NUMBER);
        return new Frames(frames);
    }

    public Frames add(Frame frame) {
        frames.add(frame);
        return new Frames(frames);
    }

    public int getNormalFrameSize() {
        return frames.size();
    }

}
