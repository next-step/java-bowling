package bowling.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    private List<NormalFrame> frames = new LinkedList<>();

    public Frames() {
    }

    public Frames(List<NormalFrame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public void addFrame(NormalFrame normalFrame) {
        frames.add(normalFrame);
    }

    public List<NormalFrame> getFrames() {
        return frames;
    }
}
