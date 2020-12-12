package bowling.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Frames {

    private final List<Frame> frames;

    private Frames() {
        this.frames = new ArrayList<>();
    }

    public static Frames of() {
        return new Frames();
    }

    public void inputFrame(Frame frame) {
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return unmodifiableList(frames);
    }

}
