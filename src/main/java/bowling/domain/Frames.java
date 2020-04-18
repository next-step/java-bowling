package bowling.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private List<Frame> frames;

    public Frames() {
        frames = new LinkedList<>();
    }

    public Frames(List<Frame> frames) {
        this.frames = Collections.unmodifiableList(new LinkedList<>(frames));
    }

    public void addFrame(Frame frame) {
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
