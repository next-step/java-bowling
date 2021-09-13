package step3.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> frames;

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
