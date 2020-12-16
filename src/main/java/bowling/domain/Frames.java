package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int FRAME_SIZE = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    private static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        return of(new ArrayList<>());
    }


    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
